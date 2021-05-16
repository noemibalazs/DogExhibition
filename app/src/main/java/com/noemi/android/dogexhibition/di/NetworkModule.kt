package com.noemi.android.dogexhibition.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.noemi.android.dogexhibition.DOG_BASE_URL
import com.noemi.android.dogexhibition.IMAGE_BASE_URL
import com.noemi.android.dogexhibition.api.dataSource.DogAPI
import com.noemi.android.dogexhibition.api.dataSource.DogImageAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideDogApi(
        @ForApplication context: Context,
        httpClient: OkHttpClient
    ): DogAPI {
        return Retrofit.Builder()
            .baseUrl(DOG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build().create(DogAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDogImagesApi(
        @ForApplication context: Context,
        httpClient: OkHttpClient
    ): DogImageAPI {
        return Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build().create(DogImageAPI::class.java)
    }
}