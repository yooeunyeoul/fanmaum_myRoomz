package com.fanmaum.myroomz.utils.watcher

import android.text.Spanned
import android.text.SpannedString
import kotlin.math.max

class InputLimitFilter(private val limitCount: Int, private val limitOverListener: LimitOverListener) : EditInputFilter() {

    fun interface LimitOverListener {
        fun stringLengthOver(): Unit
    }

    override fun filterEntry(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val keepStringLength = (dest?.length ?: 0) - (dend - dstart)
        val newStringLength = (end - start)
        return if (keepStringLength + newStringLength > limitCount) {
            limitOverListener.stringLengthOver()
            val subLengthCount = max(limitCount - keepStringLength, 0)
            return if (subLengthCount <= 0 || subLengthCount > source?.length ?: 0) "" else source?.subSequence(0, subLengthCount)
        } else SpannedString(source)
    }
}