package com.ghrisav.minibaseproject.app.di

import com.ghrisav.minibaseproject.data.remote.di.remoteModule
import com.ghrisav.minibaseproject.features.home.di.featureHomeModule

val appComponent = listOf(appModule, remoteModule, featureHomeModule)