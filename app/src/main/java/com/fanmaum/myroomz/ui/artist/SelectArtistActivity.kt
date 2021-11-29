package com.fanmaum.myroomz.ui.artist

import android.content.Intent
import androidx.activity.viewModels
import com.fanmaum.myroomz.base.BaseDataBindingActivity
import com.fanmaum.myroomz.databinding.ActivitySelectArtistBinding
import com.fanmaum.myroomz.ui.NicknameSettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectArtistActivity : BaseDataBindingActivity<ActivitySelectArtistBinding>({ActivitySelectArtistBinding.inflate(it)}) {
    private val viewModel: SelectArtistViewModel by viewModels()

    override fun bindingAfter() {
        binding.viewModel = viewModel
    }

    override fun bindingBefore() {
        viewModel.getArtistList()
        viewModel.hideKeyboard.observe(this, {
            if (it == null) return@observe
            hideKeyboard(it)
        })
    }

    override fun initStartView() {
        binding.artistList.adapter = viewModel.adapter
        binding.artistSelectNextBtn.setOnClickListener {
            startActivity(Intent(this, NicknameSettingActivity::class.java))
        }
    }
}