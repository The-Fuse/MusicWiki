package com.rohit.musicwiki

import android.app.Application
import com.rohit.musicwiki.di.ApplicationComponent
import com.rohit.musicwiki.di.DaggerApplicationComponent

class MainApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}