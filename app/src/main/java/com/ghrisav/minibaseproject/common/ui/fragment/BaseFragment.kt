package com.ghrisav.minibaseproject.common.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ghrisav.minibaseproject.common.extensions.setupSnackbar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), BaseFragmentInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateFragment()
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
        onViewCreatedFragment()
    }

    /* Public functions */
    fun setLoading(visible: Boolean) = applyIntoBaseActivity(activity) { it.setLoading(visible) }
    fun showToolbar() = applyIntoBaseActivity(activity) { it.showToolbar() }
    fun showBottomNavigation() = applyIntoBaseActivity(activity) { it.showBottomNavigation() }
    fun hideToolbar() = applyIntoBaseActivity(activity) { it.hideToolbar() }
    fun hideBottomNavigation() = applyIntoBaseActivity(activity) { it.hideBottomNavigation() }

    fun setOnBackPressedCallback(isEnabled: Boolean = true, handleOnBackPressed: () -> Unit) {
        val onBackPressedCallback = object : OnBackPressedCallback(isEnabled) {
            override fun handleOnBackPressed() {
                handleOnBackPressed()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    /* Abstract functions */
    abstract fun onViewCreatedFragment()

    /* Open functions */
    open fun onCreateFragment() {/* No-op */ }
}