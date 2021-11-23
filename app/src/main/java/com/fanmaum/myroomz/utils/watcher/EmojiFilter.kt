package com.fanmaum.myroomz.utils.watcher

import android.text.Spanned
import android.text.SpannedString

class EmojiFilter @JvmOverloads constructor(private val enterEnable: Boolean = false, private val listener: EmojiInputListener) : EditInputFilter() {

    fun interface EmojiInputListener {
        fun emojiInput()
    }

    override fun filterEntry(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        if (source.isNullOrEmpty()) return null
        return if (specialCharactersPattern.containsMatchIn(source.last().toString()).not()) {
            val buffer = StringBuffer()
            source.forEach {
                if (enterEnable && it == '\n') {
                    buffer.append(it)
                } else {
                    if (specialCharactersPattern.containsMatchIn(it.toString())) {
                        buffer.append(it)
                    }
                }
            }
            val bufferString = buffer.toString()
            val check1 = source.toString() != bufferString
            val check2 = bufferString == dest.toString()

            if (check1 || check2) listener.emojiInput()

            return when {
                check1 -> SpannedString(bufferString)
                check2 -> ""
                else -> SpannedString(source)
            }
        } else SpannedString(source)
    }
}