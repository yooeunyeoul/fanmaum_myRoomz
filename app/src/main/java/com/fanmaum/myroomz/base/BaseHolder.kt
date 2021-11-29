package com.fanmaum.myroomz.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<VB : ViewDataBinding, E : Any>(protected val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context
        get() = itemView.context

    lateinit var element:E

    open fun bind(element: E) {
        this.element = element
    }
}