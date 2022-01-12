package com.fanmaum.myroomz.ui.room

import android.content.Intent
import android.graphics.Typeface
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.FragmentMyRoomBinding
import com.fanmaum.myroomz.di.DiModule
import com.fanmaum.myroomz.dialog.CenterCustomDialog
import com.fanmaum.myroomz.dialog.EmojiLikeDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserRoomActivity :
    BaseActivity<FragmentMyRoomBinding>({ FragmentMyRoomBinding.inflate(it) }) {

    private val roomViewModel: RoomViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = roomViewModel

    @Inject
    lateinit var visitingListAdapterFactory: DiModule.AdapterFactory
    private val visitingListAdapter by lazy { visitingListAdapterFactory.create(isMyRoom = true, fm = supportFragmentManager) }

    override fun initViewBinding() {


        with(binding) {
            vm = roomViewModel
            commentListView.adapter = visitingListAdapter.apply {
                listener = { comment ->
                    roomViewModel.changeData()
                }
            }

        }
    }

    override fun bindingBefore() {
        with(roomViewModel) {

            commentListLiveData.observe(this@UserRoomActivity, Observer {
                visitingListAdapter.submitList(it)
//                visitingListAdapter.notifyDataSetChanged()
            })

            viewAllContentLiveData.observe(this@UserRoomActivity, Observer {
                CenterCustomDialog().apply {
                    titleText ="아아아아"
                    contentText = "이이이이"
                    topBottomText="오오오오"
                    bottomBottomText="이이이이이이잉"
                    topBottomButtonColor = R.color.secondary_yellow
                    topBottomButtonTypeFace = Typeface.BOLD_ITALIC
                }.show(supportFragmentManager,"custom dialog")
            })

            viewAllVisitingBookLiveData.observe(this@UserRoomActivity, Observer {
                startActivity(
                    Intent(
                        this@UserRoomActivity,
                        UserRoomActivity::class.java
                    )
                )
            })

            viewLikeLiveData.observe(this@UserRoomActivity, Observer {
                EmojiLikeDialog().show(supportFragmentManager,"emoji")
            })

        }

    }

    override fun bindingAfter() {
        with(binding) {
            backBtn.setOnClickListener {
                finish()
            }
        }

    }


}