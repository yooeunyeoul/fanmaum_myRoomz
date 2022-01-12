package com.fanmaum.myroomz.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseDataBindingActivity
import com.fanmaum.myroomz.databinding.ActivityNicknameSettingBinding
import com.fanmaum.myroomz.ui.home.HomeActivity
import com.fanmaum.myroomz.utils.watcher.EditInputFilterBuild
import com.fanmaum.myroomz.utils.watcher.EmojiFilter
import com.fanmaum.myroomz.utils.watcher.RecommendFilter

class NicknameSettingActivity : BaseDataBindingActivity<ActivityNicknameSettingBinding>({ ActivityNicknameSettingBinding.inflate(it)}) {
    override fun bindingAfter() {

    }

    override fun bindingBefore() {
    }

    override fun initStartView() {
        binding.backBtn.setOnClickListener { onBackPressed() }
        binding.editNickname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (it == ""){
                        return
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.nicknameLength.text = "${p0?.length}/12"
                p0?.let {
                    if (it.isNotEmpty()) {
                        binding.nicknameUnderline.setBackgroundResource(R.color.primary)
                        if (it.length > 12){
                            binding.editNickname.setText(binding.editNickname.text.toString().substring(0, 12))
                            binding.editNickname.setSelection(12)
                            binding.nicknameWarning.text = "12자까지만 입력할 수 있어요."
                            binding.nicknameWarning.visibility = View.VISIBLE
                        }else {
                            binding.nicknameWarning.visibility = View.GONE
                        }
                    }else {
                        binding.nicknameUnderline.setBackgroundResource(R.color.very_light_pink)
                    }
                    binding.nicknameCheck.isEnabled = it.length > 1
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.editNickname.setOnEditorActionListener { p0, p1, p2 ->
            when (p1) {
                EditorInfo.IME_ACTION_UNSPECIFIED -> {
                    hideKeyboard(binding.editNickname.windowToken)
                    true
                }
                else -> false
            }
        }
        binding.nicknameNextBtn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }



        EditInputFilterBuild().setEditInputFilter(EmojiFilter {
            binding.nicknameWarning.text = "특수문자 및 공백은 입력할 수 없습니다."
            binding.nicknameWarning.visibility = View.VISIBLE
        }).into(binding.editNickname)
    }
}