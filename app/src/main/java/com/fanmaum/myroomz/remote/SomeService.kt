package com.fanmaum.myroomz.remote

import com.fanmaum.myroomz.entries.SomeEntry
import retrofit2.Response
import retrofit2.http.GET

interface SomeService {
    @GET("some")
    suspend fun someFunctionCall() : Response<SomeEntry>
}