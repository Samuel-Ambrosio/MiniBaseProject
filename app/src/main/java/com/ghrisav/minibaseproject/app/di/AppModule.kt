package com.ghrisav.minibaseproject.app.di

import com.ghrisav.minibaseproject.app.ui.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
}