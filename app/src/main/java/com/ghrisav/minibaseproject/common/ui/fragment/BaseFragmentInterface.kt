package com.ghrisav.minibaseproject.common.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.ghrisav.minibaseproject.common.ui.activity.BaseActivity
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel
import com.ghrisav.minibaseproject.common.utils.NavigationCommand

interface BaseFragmentInterface {

    fun observeNavigation(
        viewLifecycleOwner: LifecycleOwner,
        viewModel: BaseViewModel,
        navController: NavController
    ) {
        viewModel.getNavigation().observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> navController.navigate(
                        command.directions,
                        getExtras()
                    )
                    is NavigationCommand.Back -> navController.navigateUp()
                }
            }
        })
    }

    fun observeLoading(
        viewLifecycleOwner: LifecycleOwner,
        viewModel: BaseViewModel,
        setLoading: (Boolean) -> Unit
    ) {
        viewModel.isLoading().observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { isLoading ->
                setLoading(isLoading)
            }
        })
    }

    fun applyIntoBaseActivity(activity: Activity?, action: (baseActivity: BaseActivity) -> Unit) {
        if (activity != null && activity is BaseActivity) {
            action(activity)
        }
    }

    fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    fun getViewModel(): BaseViewModel

    fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
}