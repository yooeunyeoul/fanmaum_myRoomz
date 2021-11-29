package com.fanmaum.myroomz.base

import android.graphics.Rect
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

abstract class BaseDataBindingActivity<T : ViewDataBinding>(
    val bindingFactory: (LayoutInflater) -> T
) : AppCompatActivity() {

    private var _binding: T? = null
    val binding get() = _binding!!

    protected abstract fun bindingAfter()
    protected abstract fun bindingBefore()
    protected abstract fun initStartView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        initStartView()
        bindingAfter()
        bindingBefore()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null) return false
        val focusView: View? = currentFocus
        focusView?.let {
            val rect = Rect()
            it.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                hideKeyboard(it.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun hideKeyboard(iBinder: IBinder){
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(iBinder, 0)
    }
}