package com.fanmaum.myroomz.ui.room

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.databinding.FragmentRoomBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomFragment @Inject constructor() : BaseFragment<FragmentRoomBinding>() {
    private val roomViewModel : RoomViewModel by viewModels()

    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        with(binding) {
            viewModel = roomViewModel
            roomRefreshLayout.setOnRefreshListener {
                roomRefreshLayout.isRefreshing =false

            }
        }
    }

    override fun bindingAfter() {

    }

    override fun onResume() {
        super.onResume()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding = FragmentRoomBinding.inflate(layoutInflater,container,false)


}