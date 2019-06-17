package com.example.iaia.sampleandroidapplication.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().let {
            it.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            it.replace(binding.flContainer.id, fragment, fragment.javaClass.simpleName)
            it.addToBackStack(null)
            it.commit()
        }
    }
}
