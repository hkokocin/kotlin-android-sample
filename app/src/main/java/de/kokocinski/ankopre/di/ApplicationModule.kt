package de.kokocinski.ankopre.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import de.kokocinski.ankopre.BuildConfig
import de.kokocinski.ankopre.SampleApplication
import de.kokocinski.ankopre.data.Webservice
import de.kokocinski.ankopre.data.WebserviceConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(val application: SampleApplication) {

    @Provides
    @Singleton
    fun application(): SampleApplication {
        return application
    }

    @Provides
    @Singleton
    fun resources(): Resources {
        return application.resources
    }


    @Provides
    @Singleton
    @Named("Api_Client")
    fun getOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val headerLogger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
            val bodyLogger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
            clientBuilder.addNetworkInterceptor(headerLogger)
            clientBuilder.addNetworkInterceptor(bodyLogger)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun getWebservice(@Named("Api_Client") client: OkHttpClient): Webservice {
        return Retrofit.Builder()
                .baseUrl(WebserviceConfig.ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Webservice::class.java)
    }
}