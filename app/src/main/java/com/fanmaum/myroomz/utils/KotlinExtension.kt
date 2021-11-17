package com.fanmaum.myroomz.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


fun Context?.showToast(
    @StringRes textId: Int,
    duration: Int = Toast.LENGTH_SHORT
) =
    this?.let {
        Toast.makeText(it, textId, duration).show()
    }
