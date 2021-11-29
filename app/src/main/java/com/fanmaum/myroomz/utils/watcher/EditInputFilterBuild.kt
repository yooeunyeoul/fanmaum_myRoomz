package com.fanmaum.myroomz.utils.watcher

import android.text.InputFilter
import android.text.SpannedString
import android.widget.TextView

class EditInputFilterBuild {

    private var textWatcherInitCheck: Boolean = false

    private val inputFilterList = mutableListOf<EditInputFilter>()
    private val emptyTextWatcher: TextInputAfterFiltering by lazy {
        textWatcherInitCheck = true
        TextInputAfterFiltering()
    }

    fun setEditInputFilter(filter: EditInputFilter): EditInputFilterBuild {
        inputFilterList.add(filter)
        return this
    }

    fun setNotFilteringListener(notFilteringListener: TextInputAfterFiltering.NotFilteringListener): EditInputFilterBuild {
        emptyTextWatcher.setNotFilteringListener(notFilteringListener)
        return this
    }

    fun setEmptyTextWatcherListener(emptyTextListener: TextInputAfterFiltering.EmptyMessageListener): EditInputFilterBuild {
        emptyTextWatcher.setEmptyListener(emptyTextListener)
        return this
    }


    fun into(view: TextView) {

        // filtering list setting
        if (inputFilterList.isEmpty().not()) {
            val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
                var tempSourceString = source
                inputFilterList.forEach {
                    tempSourceString =
                            it.filterEntry(tempSourceString, start, end, dest, dstart, dend)
                                    ?: tempSourceString
                }
                SpannedString(tempSourceString)
            }

            view.filters = arrayOf(inputFilter)
        }

        if (textWatcherInitCheck) view.addTextChangedListener(emptyTextWatcher)
    }

}