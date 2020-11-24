package com.ghrisav.minibaseproject.common.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ghrisav.minibaseproject.R
import com.ghrisav.minibaseproject.common.extensions.doGone
import com.ghrisav.minibaseproject.common.extensions.doVisible
import com.ghrisav.minibaseproject.common.extensions.doVisibleOrGone
import com.ghrisav.minibaseproject.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNavigation()
    }

    /* Public functions */
    fun setFullScreenLoading(visible: Boolean) = binding.baseIncludeLoading.root.doVisibleOrGone { visible }
    fun showToolbar() = binding.baseIncludeToolbar.root.doVisible()
    fun showBottomNavigation() = binding.baseViewBottomNav.doVisible()
    fun hideToolbar() = binding.baseIncludeToolbar.root.doGone()
    fun hideBottomNavigation() = binding.baseViewBottomNav.doGone()

    /* Private functions */
    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.base__host__fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.baseViewBottomNav, navController)
    }
}