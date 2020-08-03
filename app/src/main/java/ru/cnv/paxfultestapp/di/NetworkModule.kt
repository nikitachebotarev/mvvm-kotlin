package ru.cnv.paxfultestapp.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cnv.paxfultestapp.repository.source.http.ApiService
import ru.cnv.paxfultestapp.repository.source.http.ApiServiceWrapper
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun apiServiceWrapper(apiService: ApiService): ApiServiceWrapper {
        return ApiServiceWrapper(apiService)
    }

}