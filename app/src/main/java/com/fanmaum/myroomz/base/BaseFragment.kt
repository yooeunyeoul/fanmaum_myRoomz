package com.fanmaum.myroomz.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>: Fragment() {
    private var _binding : B? =null
    val binding get() = _binding!!


    protected abstract fun bindingBefore()
    protected abstract fun initViewBinding()
    protected abstract fun bindingAfter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=getFragmentBinding(inflater,container)
        (_binding as ViewDataBinding).lifecycleOwner = this
        bindingBefore()
        initViewBinding()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingAfter()
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater,container:ViewGroup?):B

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}