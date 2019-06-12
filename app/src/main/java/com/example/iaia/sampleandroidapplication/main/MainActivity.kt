package com.example.iaia.sampleandroidapplication.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityMainBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    private lateinit var controller: ItemController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        controller = ItemController()
        binding.ervMain.adapter = controller.adapter
        viewModel.items.observe(this, Observer {
            controller.setData(it)
        })

        viewModel.init()
    }
}
