package com.fanmaum.myroomz.ui.room

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fanmaum.myroomz.ui.chat.ChatFragment
import com.fanmaum.myroomz.ui.room.RoomFragment
import javax.inject.Inject

class RoomItemAdapter @Inject constructor(
    fa: FragmentActivity,
) : FragmentStateAdapter(fa) {

    @Inject
    lateinit var roomItemFragment: RoomItemFragment

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment = RoomItemFragment()
}