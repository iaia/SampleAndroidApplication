package com.example.iaia.sampleandroidapplication.feature.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.FragmentDummyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DummyFragment : Fragment() {
    companion object {
        fun newInstance() = DummyFragment()
    }

    private val model: DummyViewModel by viewModel()
    private lateinit var binding: FragmentDummyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dummy, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = model
        model.init()
    }
}
