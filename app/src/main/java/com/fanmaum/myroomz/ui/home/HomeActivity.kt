package com.fanmaum.myroomz.ui.home

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityHomeBinding
import com.fanmaum.myroomz.ui.HomeViewModel
import com.fanmaum.myroomz.utils.Resource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {

    private val homeViewModel: HomeViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = homeViewModel

    @Inject
    lateinit var bottomAdapter: HomeBottomAdapter

    override fun initViewBinding() {
        binding.mainPager.run {
            adapter = bottomAdapter
            isUserInputEnabled = false
        }

        with(binding) {
            homeBottomTab.run {
                tabGravity = TabLayout.GRAVITY_FILL
                TabLayoutMediator(this, mainPager) { tab, position ->
                    tab.run {
                        when (position) {
                            0 -> {
                                text = getString(R.string.main_room)
                                icon = ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_tabbar_home_toggle
                                )
                            }
                            1 -> {

                                text = getString(R.string.tour)
                                icon = ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_tabbar_home_toggle
                                )
                            }
                        }
                    }

                }.attach()
            }
        }
    }

    override fun bindingBefore() {
        with(homeViewModel) {
            someData.observe(this@HomeActivity, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {

                    }
                    Resource.Status.ERROR -> {
//                        showToast(R.string.app_name)
                    }
                    Resource.Status.LOADING -> {

                    }
                }
            })
        }

    }

    override fun bindingAfter() {
        homeViewModel.load()
    }


}