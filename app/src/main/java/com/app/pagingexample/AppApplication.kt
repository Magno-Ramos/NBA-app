package com.app.pagingexample

import android.app.Application
import com.app.pagingexample.di.ApplicationComponent
import com.app.pagingexample.di.DaggerApplicationComponent

class AppApplication : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
}