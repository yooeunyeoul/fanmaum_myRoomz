package com.fanmaum.myroomz.ui.room

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

class RootCategoryAdapter @Inject constructor(
    val fa: FragmentActivity,
) : FragmentStateAdapter(fa) {

    @Inject
    lateinit var roomItemFragment: RootCategoryFragment

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = RootCategoryFragment()

}