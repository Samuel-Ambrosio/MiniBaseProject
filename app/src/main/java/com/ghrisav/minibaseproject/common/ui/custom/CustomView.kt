package com.ghrisav.minibaseproject.common.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.ghrisav.minibaseproject.R
import com.ghrisav.minibaseproject.common.extensions.setMargins
import com.ghrisav.minibaseproject.databinding.CustomViewBinding

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomViewBinding =
        CustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setUpView(context, attrs)
    }

    fun getFrontView(): View? = binding.customViewContainerFront.getChildAt(0)
    fun getViewFromFrontView(nameIdView: String): View? {
        val resources = context.resources
        getFrontView()?.let { v ->
            if (v.id != View.NO_ID && resources.getResourceEntryName(v.id) == nameIdView) { return v }
            (v as? ViewGroup)?.children?.forEach { child ->
                if (child.id != View.NO_ID && resources.getResourceEntryName(child.id) == nameIdView) {
                    return child
                }
            }
        }
        return null
    }

    fun setSwipedLayout(@LayoutRes id: Int) {
        binding.customViewContainerSwiped.addView(inflate(context, id, null))
    }

    fun animateSwipe() {
        binding.customViewContainerMain.transitionToState(R.id.swiped)
    }

    private fun setUpView(context: Context, attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomView)

        val frontLayout = attributes.getResourceId(R.styleable.CustomView_frontLayout, R.layout.custom_view_empty)
        val backLeftLayout = attributes.getResourceId(R.styleable.CustomView_backLeftLayout, R.layout.custom_view_empty)
        val backRightLayout = attributes.getResourceId(R.styleable.CustomView_backRightLayout, R.layout.custom_view_empty)

        val middlePointMarginLeft = attributes.getInt(R.styleable.CustomView_middlePointMarginLeft, 0)
        val middlePointMarginRight = attributes.getInt(R.styleable.CustomView_middlePointMarginRight, 0)
        binding.customViewViewMiddleSeparator.setMargins(left = middlePointMarginLeft, right = middlePointMarginRight)

        binding.customViewContainerFront.addView(inflate(context, frontLayout, null))
        binding.customViewContainerBackLeft.addView(inflate(context, backLeftLayout, null))
        binding.customViewContainerBackRight.addView(inflate(context, backRightLayout, null))

        attributes.recycle()

        setUpMotionLayout()
    }

    private fun setUpMotionLayout() {
        binding.customViewContainerMain.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {
                    R.id.end -> motionLayout?.transitionToState(R.id.swiped)
                }
            }
        })
    }
}