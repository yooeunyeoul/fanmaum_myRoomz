package com.fanmaum.myroomz.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


fun <A> performGetOperation(networkCall: suspend () -> Resource<A?>, ): LiveData<Resource<A?>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()

        when (responseStatus.status) {

            Resource.Status.SUCCESS->{
                emit(Resource.success(responseStatus.data))
            }
            Resource.Status.ERROR ->{
                emit(Resource.error(responseStatus.message?:"In App ERROR"))
            }
            else -> emit(Resource.error(responseStatus.message?:"In App ERROR"))
        }
    }
