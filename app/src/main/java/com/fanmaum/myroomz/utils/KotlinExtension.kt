package com.fanmaum.myroomz.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.widget.Toast
import androidx.annotation.StringRes
import kotlin.math.roundToInt


fun Context?.showToast(
    @StringRes textId: Int,
    duration: Int = Toast.LENGTH_SHORT
) =
    this?.let {
        Toast.makeText(it, textId, duration).show()
    }



val Activity.screenSizeInDp: Point
    get() {
        val point = Point()
        DisplayMetrics().apply {
            // screen width in dp
            point.x = (widthPixels / density).roundToInt()

            // screen height in dp
            point.y = (heightPixels / density).roundToInt()
        }

        return point
    }
