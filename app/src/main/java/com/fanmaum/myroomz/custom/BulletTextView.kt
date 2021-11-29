package com.fanmaum.myroomz.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.utils.toDp

class BulletTextView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defAttribute: Int = android.R.style.Widget_TextView, defstyle: Int = 0)
    : AppCompatTextView(context, attributeSet, defAttribute) {

    var bulletGap = context.toDp(50)

    init {
        setAttribute(attributeSet)
    }

    private fun setAttribute(attributeSet: AttributeSet?) {
        attributeSet ?: return
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.BulletTextView)
        bulletGap = typeArray.getDimension(R.styleable.BulletTextView_bulletGap, bulletGap.toFloat()).toInt()
        typeArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        super.setPadding(bulletGap, 0, 0, 0)
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {}

    private fun Canvas.drawingBullet(drawingHeightPosition: Float) {
        drawText("•", 0f, drawingHeightPosition, paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            drawingBullet(paint.textSize)
        }
    }

}