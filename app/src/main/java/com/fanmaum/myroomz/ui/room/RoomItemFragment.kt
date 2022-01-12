package com.fanmaum.myroomz.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.data.Artist
import com.fanmaum.myroomz.databinding.FragmentRoomItemBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomItemFragment @Inject constructor() : BaseFragment<FragmentRoomItemBinding>() {
    @Inject
    lateinit var sampleAdapter: SampleAdapter

    private val roomViewModel: RoomViewModel by activityViewModels()

    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        with(binding) {
            itemListView.adapter = sampleAdapter
        }
        val artistBTS = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_bed_02)
        val artistBTS2 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_collect_book_02)
        val artistBTS3 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_food_2)
        val artistBTS4 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_frame_01)
        val artistBTS5 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_lamp_1)
        val artistBTS6 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_rug_01)
        val artistBTS7 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_table_01)
        val artistBTS8 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_wall_03)
        val artistBTS9 = Artist(name = "아아아", img = "asdf", group = "방탄", isSelect = false, drawableSampleImage = R.drawable.ic_window_01)
//        val artistNormal = Artist(name = "아아아", img = "asdf", group = "", isSelect = false)
        sampleAdapter.submitList(
            listOf(
                artistBTS,
                artistBTS2,
                artistBTS3,
                artistBTS4,
                artistBTS5,
                artistBTS6,
                artistBTS7,
                artistBTS8,
                artistBTS9
            )
        )
        sampleAdapter.listener = {
//            Glide.with(requireContext()).load(it).into()
            roomViewModel.selectedItemLiveData.postValue(it)

        }
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
    ): FragmentRoomItemBinding = FragmentRoomItemBinding.inflate(layoutInflater, container, false)


}