package com.fanmaum.myroomz.bindingUtils

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("bindData")
fun <E> bindRecyclerView(
    recyclerview: RecyclerView?,
    data: MutableList<List<E>>?
) {
    val adapter = recyclerview?.adapter
    data?.forEach {
        adapter
    }
}