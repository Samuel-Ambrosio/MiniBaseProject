package com.ghrisav.minibaseproject.common.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ghrisav.minibaseproject.common.ui.activity.BaseActivity
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel

abstract class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return onCreateBinding(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /* Public functions */
    fun setLoading(visible: Boolean) = applyIntoBaseActivity { it.setLoading(visible) }
    fun showToolbar() = applyIntoBaseActivity { it.showToolbar() }
    fun showBottomNavigation() = applyIntoBaseActivity { it.showBottomNavigation() }
    fun hideToolbar() = applyIntoBaseActivity { it.hideToolbar() }
    fun hideBottomNavigation() = applyIntoBaseActivity { it.hideBottomNavigation() }

    /* Private functions */
    private fun applyIntoBaseActivity(action: (baseActivity: BaseActivity) -> Unit) {
        if (activity is BaseActivity) {
            action(activity as BaseActivity)
        }
    }

    /* Abstract functions */
    abstract fun getViewModel(): BaseViewModel

    abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    abstract fun onViewCreatedFragment()

    /* Open functions */
    open fun onCreateFragment() {/* No-op */ }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}