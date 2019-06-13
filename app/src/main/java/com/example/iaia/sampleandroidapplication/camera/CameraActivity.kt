package com.example.iaia.sampleandroidapplication.camera

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityCameraBinding
import com.example.iaia.sampleandroidapplication.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CameraActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, CameraActivity::class.java)
    }

    private val model: CameraViewModel by viewModel()
    private val binding by lazy { DataBindingUtil.setContentView<ActivityCameraBinding>(this, R.layout.activity_camera) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = model
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
