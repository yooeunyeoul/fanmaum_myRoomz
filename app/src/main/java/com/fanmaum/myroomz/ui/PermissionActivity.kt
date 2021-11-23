package com.fanmaum.myroomz.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityPermissionBinding
import com.fanmaum.myroomz.ui.artist.SelectArtistActivity

class PermissionActivity : BaseActivity<ActivityPermissionBinding>({ ActivityPermissionBinding.inflate(it)}) {
    private val someViewModel : SomeViewModel by viewModels()

    override fun bindingAfter() {

    }

    override fun bindingBefore() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.permissionNextBtn.setOnClickListener {
            startActivity(Intent(this, SelectArtistActivity::class.java))
            finish()
        }
    }

    override val baseViewModel: BaseViewModel
        get() = someViewModel
}