package com.ghrisav.minibaseproject.features.home.di

import com.ghrisav.minibaseproject.features.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel { HomeViewModel(get()) }
}