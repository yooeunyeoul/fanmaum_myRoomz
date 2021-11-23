package com.fanmaum.myroomz.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import androidx.activity.viewModels
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) }) {

    private val someViewModel : SomeViewModel by viewModels()

    override fun bindingAfter() {

    }

    override fun bindingBefore() {

    }

    override fun initStartView() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}