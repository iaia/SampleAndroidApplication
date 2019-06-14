package com.example.iaia.sampleandroidapplication.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivitySettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    private val model: SettingsViewModel by viewModel()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySettingsBinding>(
            this,
            R.layout.activity_settings
        )
    }

    private lateinit var controller: ItemController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = model
        controller = ItemController()
        binding.ervSettings.adapter = controller.adapter

        model.items.observe(this, Observer {
            controller.setData(it)
        })

        model.init()
    }
}
