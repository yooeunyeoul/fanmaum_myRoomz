package com.fanmaum.myroomz.ui.room

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseAdapter
import com.fanmaum.myroomz.base.BaseHolder
import com.fanmaum.myroomz.data.Comment
import com.fanmaum.myroomz.databinding.ItemRoomCommenttBinding
import com.fanmaum.myroomz.dialog.BottomCustomDialog
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

class VisitingListAdapter @AssistedInject constructor(
    callback: VisitingListDiffCallback,
    @Assisted val fm:FragmentManager?=null,
    @Assisted val isMyRoom: Boolean = false
) :
    BaseAdapter<Comment>(callback) {

    lateinit var listener: (Comment) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<out ViewDataBinding, Comment> {
        return VisitingListHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_room_commentt,
                parent,
                false
            )
        )
    }

    inner class VisitingListHolder(binding: ItemRoomCommenttBinding) :
        BaseHolder<ItemRoomCommenttBinding, Comment>(binding) {
        override fun bind(element: Comment) {
            super.bind(element)
            with(binding) {
                comment = element
                isMyRoom = this@VisitingListAdapter.isMyRoom

                listOf(
                    emojiHandButton,
                    emojiTwoHandButton,
                    emojiBigSmileButton,
                    emojiHeartButton,
                    emojiSmileutton,
                    emojiTwoEyesButton
                ).forEach {
                    it.setOnClickListener { view ->
                        (view as CardView).run {
                            listener.invoke(getItem(adapterPosition))
                        }
                    }
                }
                guestImageView.setOnClickListener {
                    context.startActivity(Intent(context, UserRoomActivity::class.java))
                }
                threeDotView.setOnClickListener {
                    fm?.let { it1 -> BottomCustomDialog().show(it1,"custom bottom") }
                }
            }
        }
    }

}


class VisitingListDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
        oldItem.one == newItem.one &&
                oldItem.two == newItem.two &&
                oldItem.three == newItem.three &&
                oldItem.four == newItem.four &&
                oldItem.five == newItem.five &&
                oldItem.six == newItem.six

}