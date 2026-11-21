package com.celzero.bravedns.vpn

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

data class DohProbeResult(
    val success: Boolean,
    val domain: String,
    val resolverUrl: String,
    val latencyMs: Long,
    val resolvedIps: List<String>,
    val errorMessage: String? = null
)

object DohClient {

    private val DNS_MESSAGE_MEDIA_TYPE = "application/dns-message".toMediaType()

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    fun resolveRawBlocking(dohUrl: String, queryWire: ByteArray): Pair<ByteArray?, Long> {
        val start = System.currentTimeMillis()
        return try {
            val normalizedUrl = normalizeDohUrl(dohUrl)
            val request = Request.Builder()
                .url(normalizedUrl)
                .header("Accept", "application/dns-message")
                .post(queryWire.toRequestBody(DNS_MESSAGE_MEDIA_TYPE))
                .build()

            httpClient.newCall(request).execute().use { response ->
                val elapsed = System.currentTimeMillis() - start
                if (!response.isSuccessful) {
                    return Pair(null, elapsed)
                }
                Pair(response.body?.bytes(), elapsed)
            }
        } catch (_: Exception) {
            Pair(null, System.currentTimeMillis() - start)
        }
    }

    suspend fun probeDomain(dohUrl: String, domain: String): DohProbeResult =
        withContext(Dispatchers.IO) {
            val cleanDomain = domain.trim().lowercase().ifEmpty { "example.com" }
            val normalizedUrl = normalizeDohUrl(dohUrl)
            val queryWire = DnsPacketParser.buildDnsQueryWire(cleanDomain, qType = 1)
            val start = System.currentTimeMillis()
            try {
                val request = Request.Builder()
                    .url(normalizedUrl)
                    .header("Accept", "application/dns-message")
                    .post(queryWire.toRequestBody(DNS_MESSAGE_MEDIA_TYPE))
                    .build()

                httpClient.newCall(request).execute().use { response ->
                    val elapsed = (System.currentTimeMillis() - start).coerceAtLeast(1L)
                    if (!response.isSuccessful) {
                        return@withContext DohProbeResult(
                            success = false,
                            domain = cleanDomain,
                            resolverUrl = normalizedUrl,
                            latencyMs = elapsed,
                            resolvedIps = emptyList(),
                            errorMessage = "HTTP ${response.code} ${response.message}"
                        )
                    }
                    val bytes = response.body?.bytes()
                    val ips = if (bytes != null) DnsPacketParser.extractAnswerIps(bytes) else emptyList()
                    DohProbeResult(
                        success = true,
                        domain = cleanDomain,
                        resolverUrl = normalizedUrl,
                        latencyMs = elapsed,
                        resolvedIps = ips
                    )
                }
            } catch (e: Exception) {
                DohProbeResult(
                    success = false,
                    domain = cleanDomain,
                    resolverUrl = normalizedUrl,
                    latencyMs = System.currentTimeMillis() - start,
                    resolvedIps = emptyList(),
                    errorMessage = e.localizedMessage ?: "Network unreachable"
                )
            }
        }

    private fun normalizeDohUrl(url: String): String {
        val trimmed = url.trim()
        return when {
            trimmed.startsWith("https://") -> trimmed
            trimmed.startsWith("http://") -> trimmed.replaceFirst("http://", "https://")
            else -> "https://$trimmed"
        }
    }
}
