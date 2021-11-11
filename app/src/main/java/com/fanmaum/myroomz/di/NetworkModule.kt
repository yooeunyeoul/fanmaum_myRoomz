package com.fanmaum.myroomz.di

import com.fanmaum.myroomz.base.BaseDataSource
import com.fanmaum.myroomz.remote.SomeRemoteDataSourceImpl
import com.fanmaum.myroomz.remote.SomeService
import com.fanmaum.myroomz.repository.SomeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideSomeService(retrofit: Retrofit) : SomeService = retrofit.create(SomeService::class.java)


}