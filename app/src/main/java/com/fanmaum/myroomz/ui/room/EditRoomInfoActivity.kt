package com.fanmaum.myroomz.ui.room

import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityEidtRoomInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditRoomInfoActivity :
    BaseActivity<ActivityEidtRoomInfoBinding>({ ActivityEidtRoomInfoBinding.inflate(it) }) {

    private val viewModel: RoomInfoViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel


    override fun initViewBinding() {


        with(binding) {
            vm = viewModel

        }
    }

    override fun bindingBefore() {
        with(viewModel) {

        }

    }

    override fun bindingAfter() {
        with(binding) {

            edtRoomNameView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        viewModel.roomTitleTextLength.postValue(p0.length)
                    }

                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            edtRoomAddressVeiw.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        viewModel.roomAddressTextLength.postValue(p0.length)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            saveBtn.setOnClickListener {
                finish()
            }
        }

    }


}