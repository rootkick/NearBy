package api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Ihsib on 8/19/2020.
 */

class ApiHelper {
    companion object {
        private var retrofit: Retrofit? = null
        private const val API_URL = "https://api.foursquare.com/v2/venues/"
        const val CLIENT_ID = "T1T04V1DC0WV1XMG2MZ44SIB2GEV0T4UCJHVQME3APUOOP4Q"
        const val CLIENT_SECRET = "DDTIHVSKPJNDKKSDPDGKH5EZ4A51Z1UAGZI220BA2NUJMNOJ"

        fun getRetrofit(): Retrofit? {
            var okHttpClient: OkHttpClient? = null

            if (retrofit == null) {
                try {
                    val trustAllCerts =
                        arrayOf<TrustManager>(
                            object : X509TrustManager {
                                override fun checkClientTrusted(
                                    chain: Array<X509Certificate>,
                                    authType: String
                                ) {
                                }

                                override fun checkServerTrusted(
                                    chain: Array<X509Certificate>,
                                    authType: String
                                ) {
                                }

                                override fun getAcceptedIssuers(): Array<X509Certificate> {
                                    return arrayOf()
                                }
                            }
                        )
                    val sslContext =
                        SSLContext.getInstance("SSL")
                    sslContext.init(null, trustAllCerts, SecureRandom())
                    val sslSocketFactory =
                        sslContext.socketFactory
                    okHttpClient = OkHttpClient.Builder()
                        .addInterceptor { chain: Interceptor.Chain ->
                            val builder = chain.request().newBuilder()
                            builder.addHeader("Content-Type", "application/json")
                            builder.method(chain.request().method(), chain.request().body())
                            chain.proceed(builder.build())
                        }
                        .sslSocketFactory(
                            sslSocketFactory,
                            trustAllCerts[0] as X509TrustManager
                        )
                        .readTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .cache(null)
                        .build()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                } catch (e: KeyManagementException) {
                    e.printStackTrace()
                }

                retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }

            return retrofit
        }
    }


}