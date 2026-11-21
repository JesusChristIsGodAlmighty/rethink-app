package com.celzero.bravedns.vpn

import com.celzero.bravedns.data.db.WireGuardConfigEntity

object WireGuardParser {

    fun parse(name: String, rawConf: String): Result<WireGuardConfigEntity> {
        val lines = rawConf.lines()
        var section = ""
        var address = ""
        var privateKey = ""
        var dns = ""
        var mtu = 1280
        var peerPublicKey = ""
        var endpoint = ""
        var allowedIps = "0.0.0.0/0, ::/0"

        for (rawLine in lines) {
            val line = rawLine.substringBefore('#').trim()
            if (line.isEmpty()) continue
            if (line.startsWith("[") && line.endsWith("]")) {
                section = line.lowercase()
                continue
            }
            val eqIdx = line.indexOf('=')
            if (eqIdx <= 0) continue
            val key = line.substring(0, eqIdx).trim().lowercase()
            val value = line.substring(eqIdx + 1).trim()

            when (section) {
                "[interface]" -> when (key) {
                    "address" -> address = value
                    "privatekey" -> privateKey = value
                    "dns" -> dns = value
                    "mtu" -> mtu = value.toIntOrNull()?.coerceIn(1200, 1500) ?: 1280
                }
                "[peer]" -> when (key) {
                    "publickey" -> peerPublicKey = value
                    "endpoint" -> endpoint = value
                    "allowedips" -> allowedIps = value
                }
            }
        }

        if (privateKey.isBlank() || address.isBlank()) {
            return Result.failure(IllegalArgumentException("Missing [Interface] PrivateKey or Address"))
        }
        if (peerPublicKey.isBlank() || endpoint.isBlank()) {
            return Result.failure(IllegalArgumentException("Missing [Peer] PublicKey or Endpoint"))
        }

        val maskedKey = if (privateKey.length > 8) {
            "${privateKey.take(4)}••••${privateKey.takeLast(4)}"
        } else {
            "••••"
        }

        return Result.success(
            WireGuardConfigEntity(
                name = name.ifBlank { "WireGuard Tunnel" },
                interfaceAddress = address,
                privateKeyMasked = maskedKey,
                dnsServers = dns.ifBlank { "1.1.1.1" },
                mtu = mtu,
                peerEndpoint = endpoint,
                peerPublicKey = peerPublicKey,
                allowedIps = allowedIps,
                isActive = false,
                rawConfig = rawConf.trim()
            )
        )
    }
}
