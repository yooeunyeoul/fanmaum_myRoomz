package com.fanmaum.myroomz.ui

import androidx.lifecycle.ViewModel
import com.fanmaum.myroomz.repository.SomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SomeViewModel @Inject constructor(private val someRepository: SomeRepository) :ViewModel() {

}