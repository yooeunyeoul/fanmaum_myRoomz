package com.fanmaum.myroomz.ui

import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.repository.SomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SomeViewModel @Inject constructor(private val someRepository: SomeRepository) :BaseViewModel() {

    val someData get() = someRepository.getData()

    fun load() {
        setMessage(R.string.common_google_play_services_install_title)
        postMessage(R.string.common_google_play_services_install_title)
    }

    fun reset() {
        setMessage(R.string.appbar_scrolling_view_behavior)
    }
}
