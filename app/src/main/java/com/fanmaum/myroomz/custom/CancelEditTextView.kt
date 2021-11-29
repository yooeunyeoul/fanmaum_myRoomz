package com.fanmaum.myroomz.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.marginEnd
import com.fanmaum.myroomz.ui.attr.TextInputCheck.INPUT_TEXT_CHECK

open class CancelEditTextView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttrs: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attributeSet, defStyleAttrs) {

    private var textCheck = false

    private val drawRectCancelBox = Rect()

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (textCheck) View.mergeDrawableStates(drawableState, INPUT_TEXT_CHECK)
        filters
        return drawableState
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
    }
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused)
            try {
                setSelection(text?.length ?: 0)
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> removeTouch(event.x, event.y)
        }
        return super.onTouchEvent(event)
    }

    private fun removeTouch(x: Float, y: Float) {
        if (text?.isEmpty() == true || !hasFocus()) return
        if (drawRectCancelBox.contains(x.toInt(), y.toInt())) {
            text?.clear()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val cancelBoxSize = (context.resources.displayMetrics.density * 32).toInt()
        drawRectCancelBox.apply {
            right = measuredWidth
            left = right - cancelBoxSize
            bottom = top + cancelBoxSize
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        textCheck = text?.isNotEmpty() ?: false
        refreshDrawableState()
    }
}