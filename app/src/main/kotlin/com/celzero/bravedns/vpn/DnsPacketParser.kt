package com.celzero.bravedns.vpn

import java.net.InetAddress
import java.nio.ByteBuffer

data class ParsedIpv4UdpPacket(
    val ipHeaderLen: Int,
    val srcIp: ByteArray,
    val dstIp: ByteArray,
    val srcPort: Int,
    val dstPort: Int,
    val dnsPayload: ByteArray
)

data class ParsedDnsQuery(
    val transactionId: Int,
    val domain: String,
    val queryType: Int,
    val queryTypeName: String
)

object DnsPacketParser {

    fun parseIpv4UdpPacket(packet: ByteArray, length: Int): ParsedIpv4UdpPacket? {
        if (length < 28) return null
        val version = (packet[0].toInt() ushr 4) and 0x0F
        if (version != 4) return null
        val ihl = (packet[0].toInt() and 0x0F) * 4
        if (ihl < 20 || length < ihl + 8) return null
        val protocol = packet[9].toInt() and 0xFF
        if (protocol != 17) return null // UDP only

        val srcIp = packet.copyOfRange(12, 16)
        val dstIp = packet.copyOfRange(16, 20)
        val srcPort = ((packet[ihl].toInt() and 0xFF) shl 8) or (packet[ihl + 1].toInt() and 0xFF)
        val dstPort = ((packet[ihl + 2].toInt() and 0xFF) shl 8) or (packet[ihl + 3].toInt() and 0xFF)
        val udpLen = ((packet[ihl + 4].toInt() and 0xFF) shl 8) or (packet[ihl + 5].toInt() and 0xFF)
        val payloadStart = ihl + 8
        val payloadEnd = minOf(length, ihl + udpLen)
        if (payloadEnd <= payloadStart) return null

        return ParsedIpv4UdpPacket(
            ipHeaderLen = ihl,
            srcIp = srcIp,
            dstIp = dstIp,
            srcPort = srcPort,
            dstPort = dstPort,
            dnsPayload = packet.copyOfRange(payloadStart, payloadEnd)
        )
    }

    fun parseDnsQuery(dns: ByteArray): ParsedDnsQuery? {
        if (dns.size < 13) return null
        val txId = ((dns[0].toInt() and 0xFF) shl 8) or (dns[1].toInt() and 0xFF)
        val qdCount = ((dns[4].toInt() and 0xFF) shl 8) or (dns[5].toInt() and 0xFF)
        if (qdCount < 1) return null

        val sb = StringBuilder()
        var pos = 12
        var guard = 0
        while (pos < dns.size && guard++ < 64) {
            val len = dns[pos].toInt() and 0xFF
            if (len == 0) {
                pos++
                break
            }
            if ((len and 0xC0) != 0) return null
            pos++
            if (pos + len > dns.size) return null
            if (sb.isNotEmpty()) sb.append('.')
            sb.append(String(dns, pos, len, Charsets.US_ASCII))
            pos += len
        }
        if (pos + 4 > dns.size) return null
        val qType = ((dns[pos].toInt() and 0xFF) shl 8) or (dns[pos + 1].toInt() and 0xFF)
        return ParsedDnsQuery(
            transactionId = txId,
            domain = sb.toString().lowercase(),
            queryType = qType,
            queryTypeName = qTypeToName(qType)
        )
    }

    fun buildDnsQueryWire(domain: String, qType: Int = 1): ByteArray {
        val out = ArrayList<Byte>(64)
        val txId = (System.nanoTime() and 0xFFFF).toInt()
        out.add(((txId ushr 8) and 0xFF).toByte())
        out.add((txId and 0xFF).toByte())
        // Standard query, recursion desired (0x0100)
        out.add(0x01); out.add(0x00)
        // QDCOUNT = 1, ANCOUNT = 0, NSCOUNT = 0, ARCOUNT = 0
        out.add(0x00); out.add(0x01)
        out.add(0x00); out.add(0x00)
        out.add(0x00); out.add(0x00)
        out.add(0x00); out.add(0x00)

        domain.trim().trimEnd('.').split('.').filter { it.isNotEmpty() }.forEach { label ->
            val bytes = label.toByteArray(Charsets.US_ASCII)
            out.add(bytes.size.coerceAtMost(63).toByte())
            bytes.take(63).forEach { out.add(it) }
        }
        out.add(0x00)
        // QTYPE
        out.add(((qType ushr 8) and 0xFF).toByte())
        out.add((qType and 0xFF).toByte())
        // QCLASS IN (1)
        out.add(0x00); out.add(0x01)
        return out.toByteArray()
    }

    fun buildNxDomainResponse(queryWire: ByteArray): ByteArray {
        if (queryWire.size < 12) return queryWire
        val resp = queryWire.copyOf()
        // QR = 1, Opcode = 0, AA = 0, TC = 0, RD = 1 -> 0x81
        resp[2] = (resp[2].toInt() or 0x80).toByte()
        // RA = 1, RCODE = 3 (NXDOMAIN) -> 0x83
        resp[3] = ((resp[3].toInt() and 0x70) or 0x83).toByte()
        // Zero ANCOUNT, NSCOUNT, ARCOUNT
        for (i in 6..11) resp[i] = 0
        return resp
    }

    fun extractAnswerIps(responseWire: ByteArray): List<String> {
        if (responseWire.size < 12) return emptyList()
        val qdCount = ((responseWire[4].toInt() and 0xFF) shl 8) or (responseWire[5].toInt() and 0xFF)
        val anCount = ((responseWire[6].toInt() and 0xFF) shl 8) or (responseWire[7].toInt() and 0xFF)
        var pos = 12
        repeat(qdCount) {
            pos = skipDnsName(responseWire, pos)
            if (pos < 0 || pos + 4 > responseWire.size) return emptyList()
            pos += 4
        }
        val ips = mutableListOf<String>()
        repeat(anCount) {
            pos = skipDnsName(responseWire, pos)
            if (pos < 0 || pos + 10 > responseWire.size) return ips
            val type = ((responseWire[pos].toInt() and 0xFF) shl 8) or (responseWire[pos + 1].toInt() and 0xFF)
            val rdLength = ((responseWire[pos + 8].toInt() and 0xFF) shl 8) or (responseWire[pos + 9].toInt() and 0xFF)
            pos += 10
            if (pos + rdLength > responseWire.size) return ips
            if ((type == 1 && rdLength == 4) || (type == 28 && rdLength == 16)) {
                runCatching {
                    InetAddress.getByAddress(responseWire.copyOfRange(pos, pos + rdLength)).hostAddress
                }.getOrNull()?.let { ips.add(it) }
            }
            pos += rdLength
        }
        return ips
    }

    fun buildIpv4UdpResponsePacket(
        request: ParsedIpv4UdpPacket,
        dnsResponse: ByteArray
    ): ByteArray {
        val ipHeaderLen = 20
        val udpHeaderLen = 8
        val totalLen = ipHeaderLen + udpHeaderLen + dnsResponse.size
        val out = ByteArray(totalLen)

        // IPv4 Header
        out[0] = 0x45.toByte()
        out[1] = 0x00
        out[2] = ((totalLen ushr 8) and 0xFF).toByte()
        out[3] = (totalLen and 0xFF).toByte()
        out[4] = 0x00; out[5] = 0x01
        out[6] = 0x40.toByte(); out[7] = 0x00 // Don't fragment
        out[8] = 64 // TTL
        out[9] = 17 // UDP
        // Checksum [10..11] filled below
        // Swap src and dst IPs
        System.arraycopy(request.dstIp, 0, out, 12, 4)
        System.arraycopy(request.srcIp, 0, out, 16, 4)

        val ipChecksum = computeChecksum(out, 0, ipHeaderLen)
        out[10] = ((ipChecksum ushr 8) and 0xFF).toByte()
        out[11] = (ipChecksum and 0xFF).toByte()

        // UDP Header (swap src and dst ports)
        val udpLen = udpHeaderLen + dnsResponse.size
        out[20] = ((request.dstPort ushr 8) and 0xFF).toByte()
        out[21] = (request.dstPort and 0xFF).toByte()
        out[22] = ((request.srcPort ushr 8) and 0xFF).toByte()
        out[23] = (request.srcPort and 0xFF).toByte()
        out[24] = ((udpLen ushr 8) and 0xFF).toByte()
        out[25] = (udpLen and 0xFF).toByte()
        out[26] = 0x00; out[27] = 0x00 // UDP checksum optional for IPv4, set below
        System.arraycopy(dnsResponse, 0, out, 28, dnsResponse.size)

        val udpChecksum = computeUdpChecksum(out, 12, 16, 20, udpLen)
        out[26] = ((udpChecksum ushr 8) and 0xFF).toByte()
        out[27] = (udpChecksum and 0xFF).toByte()
        return out
    }

    private fun skipDnsName(data: ByteArray, start: Int): Int {
        var pos = start
        var guard = 0
        while (pos < data.size && guard++ < 64) {
            val len = data[pos].toInt() and 0xFF
            if (len == 0) return pos + 1
            if ((len and 0xC0) == 0xC0) return pos + 2
            pos += 1 + len
        }
        return -1
    }

    private fun computeChecksum(buf: ByteArray, offset: Int, len: Int): Int {
        var sum = 0
        var i = offset
        while (i < offset + len - 1) {
            sum += ((buf[i].toInt() and 0xFF) shl 8) or (buf[i + 1].toInt() and 0xFF)
            i += 2
        }
        if (len % 2 != 0) {
            sum += (buf[offset + len - 1].toInt() and 0xFF) shl 8
        }
        while ((sum ushr 16) != 0) {
            sum = (sum and 0xFFFF) + (sum ushr 16)
        }
        return sum.inv() and 0xFFFF
    }

    private fun computeUdpChecksum(
        packet: ByteArray,
        srcIpOffset: Int,
        dstIpOffset: Int,
        udpOffset: Int,
        udpLen: Int
    ): Int {
        val pseudo = ByteBuffer.allocate(12 + udpLen)
        pseudo.put(packet, srcIpOffset, 4)
        pseudo.put(packet, dstIpOffset, 4)
        pseudo.put(0)
        pseudo.put(17)
        pseudo.putShort(udpLen.toShort())
        pseudo.put(packet, udpOffset, udpLen)
        val arr = pseudo.array()
        val c = computeChecksum(arr, 0, arr.size)
        return if (c == 0) 0xFFFF else c
    }

    fun qTypeToName(qType: Int): String = when (qType) {
        1 -> "A"
        28 -> "AAAA"
        5 -> "CNAME"
        15 -> "MX"
        16 -> "TXT"
        65 -> "HTTPS"
        33 -> "SRV"
        12 -> "PTR"
        else -> "TYPE$qType"
    }
}
