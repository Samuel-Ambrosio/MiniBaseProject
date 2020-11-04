package com.ghrisav.minibaseproject.app

import android.app.Application
import com.ghrisav.minibaseproject.app.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appComponent)
        }
    }
}