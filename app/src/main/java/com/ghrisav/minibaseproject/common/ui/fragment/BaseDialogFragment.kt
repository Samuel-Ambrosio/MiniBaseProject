package com.ghrisav.minibaseproject.common.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ghrisav.minibaseproject.R
import com.ghrisav.minibaseproject.common.extensions.TAG
import com.ghrisav.minibaseproject.common.extensions.setupSnackbar
import com.google.android.material.snackbar.Snackbar

abstract class BaseDialogFragment : DialogFragment(), BaseFragmentInterface {

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
        observeNavigation(viewLifecycleOwner, getViewModel(), findNavController())
        observeLoading(viewLifecycleOwner, getViewModel(), this::setLoading)
        setupSnackbar(this, getViewModel().getSnackbarError(), Snackbar.LENGTH_LONG)
        onViewCreatedDialogFragment()
    }

    override fun onStart() {
        super.onStart()
        setUpWindow()
    }

    /* Public functions */
    fun setLoading(visible: Boolean) = applyIntoBaseActivity(activity) { it.setLoading(visible) }

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

    /* Abstract functions */
    abstract fun displayRectangleWidthFactor(): Float

    abstract fun displayRectangleHeightFactor(): Float

    abstract fun onViewCreatedDialogFragment()

    /* Open functions */
    open fun onCreateDialogFragment() { /* No-op */ }
}