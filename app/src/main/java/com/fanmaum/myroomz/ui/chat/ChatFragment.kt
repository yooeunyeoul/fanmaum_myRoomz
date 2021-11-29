package com.fanmaum.myroomz.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.databinding.FragmentChatBinding
import com.fanmaum.myroomz.ui.room.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment @Inject constructor() : BaseFragment<FragmentChatBinding>() {
    private val roomViewModel : RoomViewModel by viewModels()
    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        binding.viewModel = roomViewModel
    }

    override fun bindingAfter() {

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater,container,false)


}