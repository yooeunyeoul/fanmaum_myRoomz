package com.fanmaum.myroomz.ui.room

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.fanmaum.myroomz.R
import kotlinx.android.synthetic.main.tab_room_deco.view.*
import javax.inject.Inject

class RoomItemAdapter @Inject constructor(
    val fa: FragmentActivity,
) : FragmentStateAdapter(fa) {

    @Inject
    lateinit var roomItemFragment: RoomItemFragment

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment = RoomItemFragment()

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(fa).inflate(R.layout.tab_room_deco, null)

        when (position) {
            0->{
                Glide . with (fa).load(R.drawable.ic_item_category_24_my).into(view.tabIconView)
            }
            1->{
                Glide . with (fa).load(R.drawable.ic_item_category_24_like).into(view.tabIconView)
            }
            2->{
                Glide . with (fa).load(R.drawable.ic_obj_frame).into(view.tabIconView)
            }
            3->{
                Glide . with (fa).load(R.drawable.ic_obj_album).into(view.tabIconView)
            }
            4->{
                Glide . with (fa).load(R.drawable.ic_obj_collect_book).into(view.tabIconView)
            }
            5->{
                Glide . with (fa).load(R.drawable.ic_obj_computer).into(view.tabIconView)
            }
            6->{
                Glide . with (fa).load(R.drawable.ic_obj_doll).into(view.tabIconView)
            }
        }
        return view
    }
}