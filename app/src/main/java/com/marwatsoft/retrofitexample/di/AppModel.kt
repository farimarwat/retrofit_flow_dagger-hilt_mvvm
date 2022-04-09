package com.marwatsoft.retrofitexample.di

import com.marwatsoft.retrofitexample.helpers.GlobalHelper
import com.marwatsoft.retrofitexample.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Singleton
    @Provides
    fun providesApiService():ApiService =
        Retrofit.Builder()
            .baseUrl(GlobalHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}