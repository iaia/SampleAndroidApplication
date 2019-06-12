package com.example.iaia.sampleandroidapplication.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivitySplashBinding
import com.example.iaia.sampleandroidapplication.main.MainActivity
import org.koin.android.ext.android.bind
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()
    private val binding by lazy { DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.command.observe(this, Observer {
            when(it) {
                Command.GoToMain -> {
                    startActivity(MainActivity.createIntent(this))
                    finish()
                }
            }
        })

        viewModel.init()
    }
}
