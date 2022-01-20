package com.fanmaum.myroomz.ui.room

import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.data.*
import com.fanmaum.myroomz.databinding.FragmentMyRoomBinding
import com.fanmaum.myroomz.di.DiModule
import com.fanmaum.myroomz.dialog.CenterCustomDialog
import com.fanmaum.myroomz.dialog.EmojiLikeDialog
import com.fanmaum.myroomz.ui.room.roomDeco.RoomDecoActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyRoomFragment @Inject constructor() : BaseFragment<FragmentMyRoomBinding>() {

    private val roomViewModel: RoomViewModel by activityViewModels()

    @Inject
    lateinit var visitingListAdapterFactory: DiModule.AdapterFactory
    private val visitingListAdapter by lazy {
        visitingListAdapterFactory.create(
            isMyRoom = true,
            fm = childFragmentManager
        )
    }


    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        with(binding) {
            vm = roomViewModel
            roomViewModel.isThisMyRoom.postValue(true)
            commentListView.adapter = visitingListAdapter.apply {
                listener = { comment ->
                    roomViewModel.changeData()
                }
            }

        }

    }

    override fun bindingAfter() {
        with(roomViewModel) {
            isThisMyRoom.postValue(true)
            commentListLiveData.observe(this@MyRoomFragment, Observer {
                visitingListAdapter.submitList(it)
//                visitingListAdapter.notifyDataSetChanged()
            })

            viewAllContentLiveData.observe(this@MyRoomFragment, Observer {
                CenterCustomDialog().apply {
                    titleText = "아아아아"
                    contentText = "이이이이"
                    topBottomText = "오오오오"
                    bottomBottomText = "이이이이이이잉"
                    topBottomButtonColor = R.color.secondary_yellow
                    topBottomButtonTypeFace = Typeface.BOLD_ITALIC
                }.show(childFragmentManager, "custom dialog")
            })

            viewAllVisitingBookLiveData.observe(this@MyRoomFragment, Observer {
                startActivity(Intent(context, UserRoomActivity::class.java))
            })
            viewLikeLiveData.observe(this@MyRoomFragment, Observer {
                EmojiLikeDialog().show(childFragmentManager, "emoji")
            })
        }
        binding.editRoomInfoView.setOnClickListener {
            startActivity(Intent(context, EditRoomInfoActivity::class.java))
        }
        binding.navigateRoomDecoView.setOnClickListener {
            startActivity(Intent(context, RoomDecoActivity::class.java))
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyRoomBinding = FragmentMyRoomBinding.inflate(layoutInflater, container, false)


}


