package com.fanmaum.myroomz.ui.artist

import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanmaum.myroomz.data.Artist
import com.fanmaum.myroomz.repository.SomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectArtistViewModel @Inject constructor(private val someRepository: SomeRepository) :ViewModel(), SelectArtistAdapter.SelectArtistAdapterListener {

    val adapter = SelectArtistAdapter(this)
    private val list = arrayListOf<Artist>()

    private val _hideKeyboard = MutableLiveData<IBinder?>()
    val hideKeyboard : LiveData<IBinder?> = _hideKeyboard
    var lastSelectArtist: Artist? = null
    private val _nextEnabled = MutableLiveData(false)
    val nextEnabled : LiveData<Boolean> = _nextEnabled
    private val _emptyTextVisible = MutableLiveData<Boolean>(false)
    val emptyTextVisible : LiveData<Boolean> = _emptyTextVisible

    fun getArtistList(){
        list.add(Artist("수호", "", "EXO", false))
        list.add(Artist("백현", "", "EXO", false))
        list.add(Artist("시우민", "", "EXO", false))
        list.add(Artist("디오", "", "EXO", false))
        list.add(Artist("아이유", "", null, false))
        adapter.addItem(list)
    }

    override fun selectArtist(item: Artist) {
        item.isSelect = item.isSelect.not()
        if (item.isSelect && item.name != lastSelectArtist?.name) {
            lastSelectArtist?.let {
                it.isSelect = false
                adapter.updateItem(it)
            }
            lastSelectArtist = item
        }

        adapter.updateItem(item)
        _nextEnabled.postValue(lastSelectArtist?.isSelect)
    }

    override fun searchArtist(key: String?, token: IBinder?) {
        _hideKeyboard.postValue(token)
        if (key.isNullOrEmpty()){
            adapter.clearItemList()
            adapter.addItem(list)
            _emptyTextVisible.postValue(false)
        }else{
            val result = ArrayList<Artist>()
            for (i in list){
                if (i.name == key){
                    result.add(i)
                    break
                }
            }
            if (result.size > 0) {
                adapter.clearItemList()
                adapter.addItem(result)
                _emptyTextVisible.postValue(false)
            }else {
                adapter.clearItemList()
                _emptyTextVisible.postValue(true)
            }
        }
    }
}