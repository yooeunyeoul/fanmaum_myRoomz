package com.fanmaum.myroomz.utils.watcher

import android.text.Spanned

abstract class EditInputFilter {

    internal abstract fun filterEntry(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence?

}