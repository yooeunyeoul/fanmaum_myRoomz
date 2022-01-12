package com.fanmaum.myroomz.dialog

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.databinding.DialogCenterCustomBinding
import kotlinx.android.synthetic.main.dialog_bottom_button.*
import kotlinx.android.synthetic.main.dialog_bottom_button.titleTextView
import kotlinx.android.synthetic.main.dialog_center_custom.*

class CenterCustomDialog : DialogFragment() {
    private var binding: DialogCenterCustomBinding? = null
    var titleText: String? = null
    var contentText: String? = null
    var topBottomText: String? = null
    var bottomBottomText: String? = null

    @ColorRes
    var topBottomButtonColor: Int? = null
    @StyleRes
    var topBottomButtonTypeFace: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCenterCustomBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            titleTextView.run {
                if (titleText == null) {
                    visibility = View.GONE
                } else {
                    text = titleText
                }
            }
            contentTextView.run {
                if (contentText == null) {
                    visibility = View.GONE
                } else {
                    text = contentText
                }
            }
            topBottomTextView.run {
                if (topBottomText == null) {
                    visibility = View.GONE
                } else {
                    text = topBottomText
                }
            }
            bottomBottomTextView.run {
                if (bottomBottomText == null) {
                    visibility = View.GONE
                } else {
                    text = bottomBottomText
                }
                setTextColor(ContextCompat.getColor(context, topBottomButtonColor ?: R.color.black))
                setTypeface(typeface, topBottomButtonTypeFace ?: Typeface.NORMAL)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}