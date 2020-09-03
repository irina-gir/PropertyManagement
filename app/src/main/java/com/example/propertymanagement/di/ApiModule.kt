package com.example.propertymanagement.di

import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.api.EndPoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitClient() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideEndPoint(retrofit: Retrofit) : EndPoint{
        return retrofit.create(EndPoint::class.java)
    }
}