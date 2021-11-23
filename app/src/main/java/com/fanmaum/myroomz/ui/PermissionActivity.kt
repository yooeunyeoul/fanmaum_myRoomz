package com.fanmaum.myroomz.ui

import android.content.Intent
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.databinding.ActivityPermissionBinding
import com.fanmaum.myroomz.ui.artist.SelectArtistActivity

class PermissionActivity : BaseActivity<ActivityPermissionBinding>({ ActivityPermissionBinding.inflate(it)}) {
    override fun bindingAfter() {

    }

    override fun bindingBefore() {
    }

    override fun initStartView() {
        binding.permissionNextBtn.setOnClickListener {
            startActivity(Intent(this, SelectArtistActivity::class.java))
            finish()
        }
    }
}