package com.ghrisav.minibaseproject.data.remote.di

import com.ghrisav.minibaseproject.BuildConfig
import com.ghrisav.minibaseproject.data.remote.photo.PhotoRepository
import com.ghrisav.minibaseproject.data.remote.photo.PhotoWs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { interceptorProvider() }
    single { okHttpClientProvider(get()) }
    single { gsonProvider() }
    single { retrofitProvider(get(), get()) }
    single { photoServiceProvider(get()) }
    single { photoRepositoryProvider(get()) }
}

/* Retrofit configuration */
private fun interceptorProvider(): Interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun okHttpClientProvider(interceptor: Interceptor) =
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

private fun gsonProvider() =
    GsonBuilder()
        .setDateFormat("dd/MM/yyyy hh:mm")
        .create()

private fun retrofitProvider(okHttpClient: OkHttpClient, gson: Gson) =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_BASE_PATH)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

/* Services providers */
private fun photoServiceProvider(retrofit: Retrofit) = retrofit.create(PhotoWs::class.java)

/* Repositories providers */
private fun photoRepositoryProvider(photoWs: PhotoWs) = PhotoRepository(photoWs)