package com.erill.roger.remote

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun okHttpClientProvider(): OkHttpClient {
            val interceptor = run {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            }
            return OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(interceptor)
                .build()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun moshiProvider(): Moshi {
            return Moshi.Builder().build()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun retrofitProvider(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://www.speedrun.com/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
        }
    }
}