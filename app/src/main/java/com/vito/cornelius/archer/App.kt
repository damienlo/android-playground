package com.vito.cornelius.archer

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import com.vito.cornelius.feature.home.settings.data.DarkThemeRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var darkThemeRepository: DarkThemeRepository

    override fun onCreate() {
        super.onCreate()
        val owner = ProcessLifecycleOwner.get()
        darkThemeRepository.nightModeLive.observe(owner, Observer { nightMode ->
            nightMode?.let { AppCompatDelegate.setDefaultNightMode(it) }
        })
    }
}