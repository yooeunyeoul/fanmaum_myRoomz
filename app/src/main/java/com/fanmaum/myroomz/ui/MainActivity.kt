package com.fanmaum.myroomz.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityMainBinding
import com.fanmaum.myroomz.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    private val someViewModel : SomeViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = someViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        someViewModel.load()
        someViewModel.load()
    }

    override fun bindingBefore() {

    }

    override fun bindingAfter() {
        with(someViewModel) {
            someData.observe(this@MainActivity, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS->{

                    }
                    Resource.Status.ERROR->{
//                        showToast(R.string.app_name)
                    }
                    Resource.Status.LOADING->{

                    }
                }
            })
        }
    }


}