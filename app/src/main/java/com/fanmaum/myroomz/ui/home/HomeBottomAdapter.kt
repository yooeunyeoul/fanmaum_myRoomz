package com.fanmaum.myroomz.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fanmaum.myroomz.ui.chat.ChatFragment
import com.fanmaum.myroomz.ui.room.RoomFragment
import javax.inject.Inject

class HomeBottomAdapter @Inject constructor(
    fa: FragmentActivity,
) : FragmentStateAdapter(fa) {

    @Inject
    lateinit var roomFragment: RoomFragment
    @Inject
    lateinit var chatFragment: ChatFragment

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0->{
                roomFragment
            }
            1->{
                chatFragment
            }
            else -> {
                roomFragment
            }
        }
}