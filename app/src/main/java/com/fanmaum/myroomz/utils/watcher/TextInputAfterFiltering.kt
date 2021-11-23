package com.fanmaum.myroomz.utils.watcher

import android.text.Editable
import android.text.TextWatcher

class TextInputAfterFiltering : TextWatcher {

    fun interface EmptyMessageListener {
        fun emptyEvent()
    }

    fun interface NotFilteringListener {
        fun notFiltering()
    }

    private var emptyListener: EmptyMessageListener? = null
    private var notFilteringListener: NotFilteringListener? = null

    fun setEmptyListener(listener: EmptyMessageListener) {
        emptyListener = listener
    }

    fun setNotFilteringListener(listener: NotFilteringListener) {
        notFilteringListener = listener
    }




    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().isNullOrBlank()) emptyListener?.emptyEvent()
        else notFilteringListener?.notFiltering()
    }

}
