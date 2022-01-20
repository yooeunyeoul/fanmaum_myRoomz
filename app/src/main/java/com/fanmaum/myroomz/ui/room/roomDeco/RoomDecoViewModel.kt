package com.fanmaum.myroomz.ui.room.roomDeco

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.fanmaum.myroomz.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RoomDecoViewModel @Inject constructor() : BaseViewModel() {

    val isSaveButtonDisabledLiveData = MutableLiveData<Boolean>(true)
    val roomTitleTextLength = MutableLiveData<Int>()
    val roomAddressTextLength = MutableLiveData<Int>()
    val enableSaveBtnLiveData = combine(roomTitleTextLength.asFlow(), roomAddressTextLength.asFlow()){ roomTitleTextLength, roomAddressLength ->
        roomTitleTextLength!=0 && roomAddressLength !=0
    }.asLiveData()

    val selectedItemLiveData = MutableLiveData<Int>()

}