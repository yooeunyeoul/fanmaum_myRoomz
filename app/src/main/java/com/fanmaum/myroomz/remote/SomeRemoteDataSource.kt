package com.fanmaum.myroomz.remote

import com.fanmaum.myroomz.base.BaseDataSource
import com.fanmaum.myroomz.entries.SomeEntry
import com.fanmaum.myroomz.utils.Resource

abstract class SomeRemoteDataSource  : BaseDataSource()  {
        abstract suspend fun somFunctionCall() : Resource<SomeEntry>
}