package com.fanmaum.myroomz.repository

import com.fanmaum.myroomz.remote.SomeRemoteDataSource
import com.fanmaum.myroomz.remote.SomeRemoteDataSourceImpl
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//https://hyperconnect.github.io/2020/07/28/android-dagger-hilt.html
@ActivityRetainedScoped
class SomeRepository @Inject constructor(private val datasource: SomeRemoteDataSourceImpl) {
}