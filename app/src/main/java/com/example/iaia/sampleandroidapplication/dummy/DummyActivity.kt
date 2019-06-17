package com.example.iaia.sampleandroidapplication.dummy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityDummyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DummyActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, DummyActivity::class.java)
    }

    private val model: DummyViewModel by viewModel()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDummyBinding>(
            this,
            R.layout.activity_dummy
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = model

        initToolbar()

        model.init()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
