package com.fanmaum.myroomz.utils

import android.content.Context
import android.util.TypedValue

fun Context.toDp(size: Int) = (this.resources.displayMetrics.density * size).toInt()

fun dpToPx(context: Context, dp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
}