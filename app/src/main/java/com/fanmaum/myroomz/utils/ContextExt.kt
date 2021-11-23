package com.fanmaum.myroomz.utils

import android.content.Context

fun Context.toDp(size: Int) = (this.resources.displayMetrics.density * size).toInt()