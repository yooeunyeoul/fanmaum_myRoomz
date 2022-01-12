package com.fanmaum.myroomz.utils

import androidx.lifecycle.MutableLiveData

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

fun MutableLiveData<Event<Unit>>.emit() = postValue(Event(Unit))
fun <T> MutableLiveData<Event<T>>.emit(value: T)  = postValue(Event(value))