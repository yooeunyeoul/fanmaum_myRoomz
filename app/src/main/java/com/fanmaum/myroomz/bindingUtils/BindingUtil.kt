package com.fanmaum.myroomz.bindingUtils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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

@BindingAdapter("app:imageUrl", "app:placeholder")
fun ImageView.setImageSrcUrl(
    url: Any, placeholder: Drawable
) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(placeholder)
        .into(this)
}