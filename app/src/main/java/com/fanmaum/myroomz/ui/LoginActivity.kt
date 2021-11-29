package com.fanmaum.myroomz.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it)}) {
    private val someViewModel : SomeViewModel by viewModels()

    override fun bindingAfter() {

    }

    override fun bindingBefore() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginGoogle.setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java))
            finish()
        }
    }

    override val baseViewModel: BaseViewModel
        get() = someViewModel
}