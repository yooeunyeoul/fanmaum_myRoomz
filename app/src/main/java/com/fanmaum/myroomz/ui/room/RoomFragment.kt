package com.fanmaum.myroomz.ui.room

import android.annotation.SuppressLint
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.base.BaseFragment
import com.fanmaum.myroomz.databinding.FragmentRoomBinding
import com.fanmaum.myroomz.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_room_item.*
import kotlinx.android.synthetic.main.custom_room_item.view.*
import kotlinx.android.synthetic.main.fragment_room.view.*
import kotlinx.android.synthetic.main.include_room_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoomFragment @Inject constructor() : BaseFragment<FragmentRoomBinding>() {


    private val countViewList: MutableList<View> = mutableListOf()

    @Inject
    lateinit var sampleAdapter: SampleAdapter

    @Inject
    lateinit var roomItemAdapter: RoomItemAdapter

    private val roomViewModel: RoomViewModel by activityViewModels()

    private val addedViewList: MutableList<View> = mutableListOf()

    override fun bindingBefore() {

    }

    override fun initViewBinding() {
        with(binding) {
            viewModel = roomViewModel
//            roomRefreshLayout.setOnRefreshListener {
//                roomRefreshLayout.isRefreshing = false

//            }
            val adapter = RoomItemAdapter(requireActivity())
            pagerRoomItem.adapter = adapter
//            TabLayoutMediator(headerListView, pagerRoomItem) { tab, position ->
//                tab.run {
//                    text = getString(R.string.main_room)
//                    icon = ContextCompat.getDrawable(
//                        requireContext(),
//                        R.drawable.ic_tabbar_home_toggle
//                    )
//                }
//
//            }.attach()

//            binding.rootRoomView.setOnDragListener(dragListener)
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindingAfter() {
        with(roomViewModel) {
            selectedItemLiveData.observe(this@RoomFragment, {


                val itemView =
                    LayoutInflater.from(context).inflate(R.layout.custom_room_item, null, false)
                Glide.with(requireContext()).load(it)
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

        binding.toggleBtn.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {

                addedViewList.forEach {
                    val countView =
                        LayoutInflater.from(context).inflate(R.layout.custom_count, null, false)
                    countView.x = it.x
                    countView.y = it.y
                    binding.rootRoomView.addView(countView)
                    countViewList.add(countView)

                }

            } else {
                countViewList.forEach {
                    binding.rootRoomView.removeView(it)
                }
                countViewList.clear()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding = FragmentRoomBinding.inflate(layoutInflater, container, false)

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

                    with(binding) {
                        xRatio.text =
                            "X 비율 : ${(view.x).div(binding.rootRoomView.width)}"
                        yRatio.text =
                            "Y 비율 : ${(view.y).div(binding.rootRoomView.height)}"
                        xPoint.text = "x 좌표 : ${view.x}"
                        yPoint.text = "Y 좌표 : ${view.y}"
                    }


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
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x
                                customView?.y = view.y - dpToPx(context!!, 25f)
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x - dpToPx(context!!, 25f)
                                customView2?.y = view.y
                                binding.rootRoomView.addView(customView2)

                                // 1 사분면
                            } else {

                                customView =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x
                                customView?.y = view.y + view.height
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x - dpToPx(context!!, 25f)
                                customView2?.y = view.y + view.height - dpToPx(context!!, 25f)
                                binding.rootRoomView.addView(customView2)


                            }
                            // 2, 3 사분면
                        } else {

                            // 3 사분면
                            if (view.y.div(binding.rootRoomView.height) > 0.5) {
                                customView =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x + view.width
                                customView?.y = view.y
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x + view.width - dpToPx(context!!, 25f)
                                customView2?.y = view.y - dpToPx(context!!, 25f)
                                binding.rootRoomView.addView(customView2)


                                // 2 사분면
                            } else {

                                customView =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)

                                customView?.x = view.x + view.width - dpToPx(context!!, 25f)
                                customView?.y = view.y + view.height
                                binding.rootRoomView.addView(customView)

                                customView2 =
                                    LayoutInflater.from(context)
                                        .inflate(R.layout.custom_bar_one, null, false)
                                customView2?.x = view.x + view.width
                                customView2?.y = view.y + view.height - dpToPx(context!!, 25f)
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


