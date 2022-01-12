package com.fanmaum.myroomz.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseAdapter
import com.fanmaum.myroomz.base.BaseHolder
import com.fanmaum.myroomz.data.Artist
import com.fanmaum.myroomz.databinding.ItemSampletBinding
import com.fanmaum.myroomz.databinding.ItemSampletHeaderBinding
import javax.inject.Inject

class SampleAdapter @Inject constructor(callback: SampleDiffCallback) :
    BaseAdapter<Artist>(callback) {

    lateinit var listener : (Int?) ->Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<out ViewDataBinding, Artist> {
        return when (viewType) {
            R.layout.item_samplet -> {
                when (viewType) {
                    R.layout.item_samplet -> {
                        SampleHolder(
                            DataBindingUtil.inflate(
                                LayoutInflater.from(parent.context),
                                viewType,
                                parent,
                                false
                            )
                        )
                    }
                    R.layout.item_samplet_header -> {
                        SampleHeaderHolder(
                            DataBindingUtil.inflate(
                                LayoutInflater.from(parent.context),
                                viewType,
                                parent,
                                false
                            )
                        )
                    }
                    else -> {
                        throw IllegalStateException("존재하지 안흔 viewType : $viewType")
                    }

                }
                SampleHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                    )
                )
            }
            R.layout.item_samplet_header -> {
                SampleHeaderHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw IllegalStateException("존재하지 안흔 viewType : $viewType")
            }

        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).group) {
            "방탄" -> {
                R.layout.item_samplet
            }
            else -> {
                R.layout.item_samplet_header
            }
        }
    }

    inner class SampleHolder(binding: ItemSampletBinding) : BaseHolder<ItemSampletBinding, Artist>(binding) {
        override fun bind(element: Artist) {
            super.bind(element)
            with(binding) {
                artist = element
                Glide.with(context).load(element.drawableSampleImage).into(objectImageView)
                objectImageView.setOnClickListener {
                    listener.invoke(element.drawableSampleImage)
                }

            }
        }
    }

}


class SampleHeaderHolder(binding: ItemSampletHeaderBinding) :
    BaseHolder<ItemSampletHeaderBinding, Artist>(binding) {
    override fun bind(element: Artist) {
        super.bind(element)
        binding.artist = element
    }
}

class SampleDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem.name == newItem.name && oldItem.group == oldItem.group

}