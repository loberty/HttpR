package cn.loberty.httplibrary

import android.annotation.SuppressLint
import android.text.TextUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Create by WangChen on 2020/12/11
 */
class OkHttpHelper {

    val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(BaseParamsInterceptor())
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .sslSocketFactory(sslFactory, MyTrustManager())
        .hostnameVerifier(MyHostnameVerifier())
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val sslFactory: SSLSocketFactory
        get() {
            var sslFactory: SSLSocketFactory? = null
            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, arrayOf<TrustManager>(MyTrustManager()), SecureRandom())
                sslFactory = sslContext.socketFactory
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }
            return sslFactory!!
        }

    private class MyTrustManager : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }

    private class MyHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return if (TextUtils.isEmpty(hostname)) {
                false
            } else !listOf(*VERIFY_HOST_NAME_ARRAY)
                .contains(hostname)
        }
    }

    companion object {
        private val VERIFY_HOST_NAME_ARRAY = arrayOf("...")
        private const val TIME_OUT = 25L
        val instance = OkHttpHelper()
    }
}