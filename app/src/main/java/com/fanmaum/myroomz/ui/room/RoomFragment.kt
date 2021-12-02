package com.fanmaum.myroomz.ui.room

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.data.Artist
import com.fanmaum.myroomz.databinding.FragmentRoomBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomFragment @Inject constructor() : BaseFragment<FragmentRoomBinding>() {
    @Inject
    lateinit var sampleAdapter: SampleAdapter

    private val roomViewModel: RoomViewModel by viewModels()

    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        with(binding) {
            viewModel = roomViewModel
            roomRefreshLayout.setOnRefreshListener {
                roomRefreshLayout.isRefreshing = false

            }
            sampleListView.adapter = sampleAdapter
        }
        val artistBTS = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false)
        val artistNormal = Artist(name = "아아아", img = "asdf", group = "", isSelect = false)
        sampleAdapter.submitList(
            listOf(
                artistBTS,
                artistNormal,
                artistNormal,
                artistNormal,
                artistNormal,
                artistNormal,
                artistNormal,
                artistNormal,
                artistNormal
            )
        )
    }

    override fun bindingAfter() {
        1

    }

    override fun onResume() {
        super.onResume()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding = FragmentRoomBinding.inflate(layoutInflater, container, false)


}