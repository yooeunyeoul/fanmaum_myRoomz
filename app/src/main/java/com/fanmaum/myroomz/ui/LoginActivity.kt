package com.fanmaum.myroomz.ui

import android.content.Intent
import android.os.Bundle
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it)}) {
    override fun bindingAfter() {

    }

    override fun bindingBefore() {
    }

    override fun initStartView() {
        binding.loginGoogle.setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java))
            finish()
        }
    }
}