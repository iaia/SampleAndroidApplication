package com.example.iaia.sampleandroidapplication.feature.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.FragmentDummyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DummyFragment : Fragment() {
    private val model: DummyViewModel by viewModel()
    private lateinit var binding: FragmentDummyBinding
    private lateinit var controller: EmployeeController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dummy, container, false)
        controller = EmployeeController()
        binding.rvEmployee.apply {
            clipChildren = false
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = model

        model.employees.observe(viewLifecycleOwner, Observer {
            controller.setData(it)
        })
        model.init()
    }
}
