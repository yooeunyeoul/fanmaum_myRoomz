package com.fanmaum.myroomz.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(
    val bindingFactory: (LayoutInflater) -> B
) : AppCompatActivity() {

    private var _binding: B? = null
    val binding get() = _binding!!

    protected abstract fun bindingAfter()
    protected abstract fun bindingBefore()
    protected abstract fun initStartView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        initStartView()
        bindingAfter()
        bindingBefore()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}