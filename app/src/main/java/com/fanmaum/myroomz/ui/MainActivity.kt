package com.fanmaum.myroomz.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    private val someViewModel : SomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun bindingAfter() {

    }

    override fun bindingBefore() {

    }

    override fun initStartView() {
    }
}