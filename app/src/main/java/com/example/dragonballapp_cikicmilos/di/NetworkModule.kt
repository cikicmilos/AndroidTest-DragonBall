package com.example.dragonballapp_cikicmilos.di

import com.example.dragonballapp_cikicmilos.data.NetworkConnectivityHelper
import com.example.dragonballapp_cikicmilos.data.remote.DragonBallApiService
import com.example.dragonballapp_cikicmilos.data.remote.RemoteDataSource
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("locale", "en")
                    .build()
                chain.proceed(original.newBuilder().url(url).build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(DragonBallApiService::class.java)
    }

    single { RemoteDataSource(get()) }

    single { NetworkConnectivityHelper(androidContext()) }
}
