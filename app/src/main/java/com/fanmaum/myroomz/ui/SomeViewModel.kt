package com.fanmaum.myroomz.ui

import androidx.lifecycle.liveData
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.repository.SomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SomeViewModel @Inject constructor(private val someRepository: SomeRepository) :BaseViewModel() {

    val someData get() = someRepository.getData()

}