package com.ghrisav.minibaseproject.common.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ghrisav.minibaseproject.R
import com.ghrisav.minibaseproject.common.extensions.TAG
import com.ghrisav.minibaseproject.common.ui.activity.BaseActivity
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel

abstract class BaseDialogFragment: DialogFragment() {

    companion object {
        const val WRAP_CONTENT = -1f
        const val MATCH_PARENT = 1f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FadeDialog)
        onCreateDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateBinding(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreatedDialogFragment()
    }

    override fun onStart() {
        super.onStart()
        setUpWindow()
    }

    /* Public functions */
    fun setLoading(visible: Boolean) = applyIntoBaseActivity { it.setLoading(visible) }

    /* Private functions */
    private fun setUpWindow() {
        try {
            val displayRectangle = Rect()
            dialog?.window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)
            dialog?.window?.setLayout(
                    setUpLayoutMeasure(displayRectangle.width(), displayRectangleWidthFactor()),
                    setUpLayoutMeasure(displayRectangle.height(), displayRectangleHeightFactor())
            )
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Error setting up window.")
        }
    }

    private fun setUpLayoutMeasure(layoutMeasure: Int, layoutFactor: Float): Int {
        return if (layoutFactor == WRAP_CONTENT) {
            WindowManager.LayoutParams.WRAP_CONTENT
        } else {
            (layoutMeasure * layoutFactor).toInt()
        }
    }

    private fun applyIntoBaseActivity(action: (baseActivity: BaseActivity) -> Unit) {
        if (activity is BaseActivity) {
            action(activity as BaseActivity)
        }
    }

    /* Abstract functions */
    abstract fun displayRectangleWidthFactor(): Float

    abstract fun displayRectangleHeightFactor(): Float

    abstract fun getViewModel(): BaseViewModel

    abstract fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract fun onViewCreatedDialogFragment()

    /* Open functions */
    open fun onCreateDialogFragment() { /* No-op */ }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}