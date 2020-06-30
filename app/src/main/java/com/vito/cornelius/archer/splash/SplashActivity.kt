package com.vito.cornelius.archer.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vito.cornelius.archer.databinding.ActivitySplashscreenBinding
import com.vito.cornelius.core.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Navigation.intentRegistration(this))
        finish()
    }
}