package com.fanmaum.myroomz.ui

import androidx.lifecycle.*
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.entries.SomeEntry
import com.fanmaum.myroomz.repository.SomeRepository
import com.fanmaum.myroomz.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val someRepository: SomeRepository) :BaseViewModel() {

    val someData get() = someRepository.getData()
    val data : LiveData<Resource<SomeEntry>>
    get() = _data
    private val _data = MutableLiveData<Resource<SomeEntry>>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = someRepository.getData()
//            _data.postValue(data)

        }
    }

    fun load() {
        setMessage(R.string.app_name)
    }

    fun reset() {
        setMessage(R.string.appbar_scrolling_view_behavior)
    }
}
