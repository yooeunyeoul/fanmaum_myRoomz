package com.fanmaum.myroomz.di

import androidx.fragment.app.FragmentManager
import com.fanmaum.myroomz.ui.room.VisitingListAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.assisted.AssistedFactory
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://fanmaum.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @AssistedFactory
    interface AdapterFactory{
        fun create(isMyRoom : Boolean, fm : FragmentManager) : VisitingListAdapter
    }





}