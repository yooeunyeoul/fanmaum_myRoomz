package com.fanmaum.myroomz.remote

import com.fanmaum.myroomz.entries.SomeEntry
import com.fanmaum.myroomz.utils.Resource
import javax.inject.Inject

class SomeRemoteDataSourceImpl @Inject constructor (
    private val someService: SomeService) :SomeRemoteDataSource() {
    override suspend fun somFunctionCall(): Resource<SomeEntry> {
        return getResult { someService.someFunctionCall() }
    }


}