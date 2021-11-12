package com.fanmaum.myroomz.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(
    val bindingFactory: (LayoutInflater) -> B
) : AppCompatActivity() {

    private var _binding: B? = null
    val binding get() = _binding!!

    protected abstract fun bindingAfter()
    protected abstract fun bindingBefore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        bindingBefore()
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        bindingAfter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}