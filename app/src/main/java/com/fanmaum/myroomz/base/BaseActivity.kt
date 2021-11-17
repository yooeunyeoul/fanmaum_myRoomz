package com.fanmaum.myroomz.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.fanmaum.myroomz.utils.showToast

abstract class BaseActivity<B : ViewBinding>(
    val bindingFactory: (LayoutInflater) -> B
) : AppCompatActivity() {

    abstract val baseViewModel: BaseViewModel
    private var _binding: B? = null
    val binding get() = _binding!!

    protected abstract fun bindingBefore()
    protected abstract fun bindingAfter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        bindingBefore()
        setContentView(binding.root)

    }


    protected open fun  initObservers(){

        baseViewModel?.message.observe(this, { event->
            event.getContentIfNotHandled()?.let {message->
                this@BaseActivity.showToast(message)

            }
        })
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