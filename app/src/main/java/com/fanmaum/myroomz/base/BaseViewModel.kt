package com.fanmaum.myroomz.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanmaum.myroomz.utils.Event

abstract class BaseViewModel : ViewModel() {

    private val _message = MutableLiveData<Event<Int>>()
    val message : LiveData<Event<Int>>
    get() = _message

    // Post in background thread
    fun postMessage(@StringRes message: Int) {
        _message.postValue(Event(message))
    }

    // Post in main thread
    fun setMessage(@StringRes message: Int) {
        _message.value = Event(message)
    }
}