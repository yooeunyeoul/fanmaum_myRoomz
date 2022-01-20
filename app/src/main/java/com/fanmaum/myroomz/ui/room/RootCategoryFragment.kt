package com.fanmaum.myroomz.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.databinding.FragmentRootCategoryBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RootCategoryFragment @Inject constructor() : BaseFragment<FragmentRootCategoryBinding>() {


    private val roomViewModel: RoomViewModel by activityViewModels()

    @Inject
    lateinit var roomItemAdapter: RoomItemAdapter

    override fun bindingBefore() {

    }

    override fun initViewBinding() {


        with(binding) {
            val adapter = RoomItemAdapter(requireActivity())
            pagerRoomItem.adapter = adapter
            TabLayoutMediator(headerListView, pagerRoomItem) { tab, position ->
                tab.run {
                    when (position) {
                        0->{
                            tab.customView = adapter.getTabView(position)
                        }
                        1->{
                            tab.customView = adapter.getTabView(position)
                        }
                        2->{
                            tab.customView = adapter.getTabView(position)
                        }
                        3->{
                            tab.customView = adapter.getTabView(position)
                        }
                        4->{
                            tab.customView = adapter.getTabView(position)
                        }
                        5->{
                            tab.customView = adapter.getTabView(position)
                        }
                        6->{
                            tab.customView = adapter.getTabView(position)
                        }
                        7->{
                            tab.customView = adapter.getTabView(position)
                        }
                    }
                }

            }.attach()
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
    ): FragmentRootCategoryBinding = FragmentRootCategoryBinding.inflate(layoutInflater, container, false)


}