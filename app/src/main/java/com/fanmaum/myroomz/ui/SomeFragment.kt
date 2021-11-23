package com.fanmaum.myroomz.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fanmaum.myroomz.databinding.FragmentSomeBinding
import com.fanmaum.myroomz.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SomeFragment : BaseFragment<FragmentSomeBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSomeBinding = FragmentSomeBinding.inflate(layoutInflater,container,false)

    override fun bindingAfter() {

    }

    override fun bindingBefore() {

    }

}