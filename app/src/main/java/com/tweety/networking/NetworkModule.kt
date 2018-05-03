package com.tweety.networking

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tweety.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule(private var cacheFile: File) {

    @Provides
    @Singleton
    fun provideCall(): Retrofit {
        var cache: Cache? = null
        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .cache(cache)
                .build()


        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(
            retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesService(networkService: NetworkService): ServiceClass {
        return ServiceClass(networkService)
    }

}
