package com.fanmaum.myroomz.utils.watcher

import android.text.Spanned
import android.text.SpannedString

class RecommendFilter @JvmOverloads constructor(private val enterEnable: Boolean = false, private val listener: EmojiInputListener) : EditInputFilter() {

    fun interface EmojiInputListener {
        fun emojiInput()
    }

    override fun filterEntry(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        if (source == null) return null
        return if (recommendCharactersAndEmojiPattern.containsMatchIn(source)) {
            val buffer = StringBuffer()
            source.forEach {
                if (enterEnable && it == '\n') {
                    buffer.append(it)
                } else {
                    if (recommendCharactersAndEmojiPattern.containsMatchIn(it.toString()).not()) {
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