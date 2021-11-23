package com.fanmaum.myroomz.di

import com.fanmaum.myroomz.remote.SomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideSomeService(retrofit: Retrofit) : SomeService = retrofit.create(SomeService::class.java)


}