package com.example.iaia.sampleandroidapplication.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.camera.CameraActivity
import com.example.iaia.sampleandroidapplication.databinding.ActivityMainBinding
import com.example.iaia.sampleandroidapplication.settings.SettingsActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
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
        controller = ItemController(viewModel)
        binding.ervMain.adapter = controller.adapter
        viewModel.items.observe(this, Observer {
            controller.setData(it)
        })
        viewModel.command.observe(this, Observer {
            when (it) {
                Command.GoToCamera -> startActivity(CameraActivity.createIntent(this))
                Command.GoToLicense -> startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                Command.GoToSettings -> startActivity(SettingsActivity.createIntent(this))
            }
        })

        viewModel.init()
    }
}
