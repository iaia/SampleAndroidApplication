package com.example.iaia.sampleandroidapplication.feature.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivitySplashBinding
import com.example.iaia.sampleandroidapplication.feature.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySplashBinding>(
            this,
            R.layout.activity_splash
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.command.observe(this, Observer {
            when (it) {
                Command.GoToMain -> {
                    startActivity(MainActivity.createIntent(this))
                    finish()
                }
            }
        })

        viewModel.init()
    }
}
