package com.fanmaum.myroomz.ui.room.roomDeco

import android.annotation.SuppressLint
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseActivity
import com.fanmaum.myroomz.base.BaseViewModel
import com.fanmaum.myroomz.databinding.ActivityRoomDecoBinding
import com.fanmaum.myroomz.ui.room.RootCategoryAdapter
import com.fanmaum.myroomz.utils.dpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_room_deco.*
import kotlinx.android.synthetic.main.custom_room_item.view.*

@AndroidEntryPoint
class RoomDecoActivity :
    BaseActivity<ActivityRoomDecoBinding>({ ActivityRoomDecoBinding.inflate(it) }) {

    private val roomDecoViewModel: RoomDecoViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = roomDecoViewModel

    private val addedViewList: MutableList<View> = mutableListOf()


    override fun bindingBefore() {
        with(roomDecoViewModel) {
            with(binding) {
                backTitleBtn.saveBtn.visibility = View.VISIBLE
                backTitleBtn.titleTextView.text = getString(R.string.room_deco)
                backTitleBtn.rootView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@RoomDecoActivity,
                        R.color.heeeff4
                    )
                )
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor =
                    ContextCompat.getColor(this@RoomDecoActivity, R.color.heeeff4)
            }
        }
    }

    override fun initViewBinding() {
        with(binding) {
            viewModel = roomDecoViewModel
            setBottomSheetPeekHeight()
            val adapter = RootCategoryAdapter(this@RoomDecoActivity)
            pagerRootCategory.adapter = adapter

            TabLayoutMediator(tabObjectView, pagerRootCategory) { tab, position ->
                tab.run {
                    when (position) {
                        0 -> {
                            tab.text = getString(R.string.tab_object)
                        }
                        1 -> {
                            tab.text = getString(R.string.tab_interior)
                        }

                    }
                }

            }.attach()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setBottomSheetPeekHeight() {

        val widthPixels =
            resources.displayMetrics.widthPixels.toFloat() - dpToPx(this@RoomDecoActivity, 32f)
        val heightPixels =getSystemService(WindowManager::class.java).currentWindowMetrics.bounds.height()
        val standardWidthPixel = dpToPx(this@RoomDecoActivity, 328f).toFloat() // 기준 pixel
        val standardHeightPixel = dpToPx(this@RoomDecoActivity, 364f).toFloat() // 기준 pixel
        val ratio = widthPixels.div(standardWidthPixel)
        val roomWidth = standardWidthPixel * ratio
        val roomHeight = standardHeightPixel * ratio
        val bottomSheetPeekHeight =
            resources.displayMetrics.heightPixels - roomHeight.toInt() - dpToPx(
                this@RoomDecoActivity,
                92f
            )
        binding.bottomSheetView.layoutParams.height =
            bottomSheetPeekHeight + (roomHeight / 2).toInt()
        BottomSheetBehavior.from(binding.bottomSheetView).run {
            setPeekHeight(bottomSheetPeekHeight)
        }
        binding.rootRoomView.layoutParams.width = roomWidth.toInt()
        binding.rootRoomView.layoutParams.height = roomHeight.toInt()

        binding.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            System.out.println("Activity height : +${binding.rootView.height} Screen Height : ${rootView.rootView.height} , heightPixels : ${heightPixels}" )
        }

    }

    override fun bindingAfter() {
        with(roomDecoViewModel) {

            selectedItemLiveData.observe(this@RoomDecoActivity, {
                val itemView =
                    LayoutInflater.from(this@RoomDecoActivity).inflate(R.layout.custom_room_item, null, false)
                Glide.with(this@RoomDecoActivity).load(it)
                    .into(itemView.itemImageView)
                binding.rootRoomView.addView(itemView)
                addedViewList.add(itemView)
                itemView.run {
                    setOnTouchListener(
                        CustomTouchListener(
                            binding.rootRoomView.width,
                            binding.rootRoomView.height,
                            binding.rootRoomView,
                            itemView
                        )
                    )
                    setOnLongClickListener {
//                        Toast.makeText(context, "눌렸당", Toast.LENGTH_SHORT).show()
                        true
                    }

                }

                itemView.run {


//                    four_delete.setOnClickListener {
//                        binding.rootRoomView.removeView(itemView)
//                    }
//                    four_rotate.setOnClickListener {
//                        if (itemImageView.scaleX == -1f) {
//                            itemImageView.scaleX = 1f
//                        } else {
//                            itemImageView.scaleX = -1f
//                        }
//                    }
                }


            })


        }

    }

    inner class CustomTouchListener(
        val screenWidth: Int,
        val screenHeight: Int,
        val rootView: View,
        val itemView: View
    ) : View.OnTouchListener {
        private var timeWhenDown: Long = 0
        private var dX: Float = 0f
        private var dY: Float = 0f

        var customView: View? = null
        var customView2: View? = null

        @SuppressLint("SetTextI18n")
        override fun onTouch(view: View, event: MotionEvent): Boolean {

            val newX: Float
            val newY: Float

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.bringToFront()
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY

                    timeWhenDown = System.currentTimeMillis()

                }
                MotionEvent.ACTION_MOVE -> {

                    customView?.let {
                        binding.rootRoomView.removeView(customView)
                        binding.rootRoomView.removeView(customView2)
                        customView = null
                        customView2 = null
                    }



                    rootView.alpha = 0.9f
                    newX = event.rawX + dX
                    newY = event.rawY + dY


                    if ((newX <= 0 || newX >= screenWidth - view.width) || (newY <= 0 || newY >= screenHeight - view.height)) {
                        return true
                    }

                    view.animate()
                        .x(newX)
                        .y(newY)
                        .setDuration(0)
                        .start()
                }
                MotionEvent.ACTION_UP -> {
                    if (System.currentTimeMillis() - timeWhenDown < 200) {


                        // 1, 4 사분면
                        if ((view.x).div(binding.rootRoomView.width) > 0.5) {

                            // 4분면
                            if (view.y.div(binding.rootRoomView.height) > 0.5) {
                                customView =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x
                                customView?.y = view.y - dpToPx(this@RoomDecoActivity!!, 25f)
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x - dpToPx(this@RoomDecoActivity!!, 25f)
                                customView2?.y = view.y
                                binding.rootRoomView.addView(customView2)

                                // 1 사분면
                            } else {

                                customView =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x
                                customView?.y = view.y + view.height
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x - dpToPx(this@RoomDecoActivity!!, 25f)
                                customView2?.y = view.y + view.height - dpToPx(this@RoomDecoActivity!!, 25f)
                                binding.rootRoomView.addView(customView2)


                            }
                            // 2, 3 사분면
                        } else {

                            // 3 사분면
                            if (view.y.div(binding.rootRoomView.height) > 0.5) {
                                customView =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x + view.width
                                customView?.y = view.y
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x + view.width - dpToPx(this@RoomDecoActivity!!, 25f)
                                customView2?.y = view.y - dpToPx(this@RoomDecoActivity!!, 25f)
                                binding.rootRoomView.addView(customView2)


                                // 2 사분면
                            } else {

                                customView =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x + view.width - dpToPx(this@RoomDecoActivity!!, 25f)
                                customView?.y = view.y + view.height
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(this@RoomDecoActivity)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x + view.width
                                customView2?.y = view.y + view.height - dpToPx(this@RoomDecoActivity!!, 25f)
                                binding.rootRoomView.addView(customView2)

                            }
                        }

                    } else {


                    }
                    rootView.alpha = 1.0f

                }
            }
            return false
        }
    }



}