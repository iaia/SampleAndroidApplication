package com.example.iaia.sampleandroidapplication.feature.mainmenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iaia.sampleandroidapplication.R
import com.example.iaia.sampleandroidapplication.databinding.FragmentMainBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val model: MainFragmentViewModel by viewModel()
    lateinit var binding: FragmentMainBinding
    private lateinit var controller: ItemController
    private val sharedViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        controller = ItemController(model)
        binding.rvMain.apply {
            clipChildren = false
            setHasFixedSize(true)
            setRecycledViewPool(sharedViewPool)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = model

        model.items.observe(viewLifecycleOwner, Observer {
            controller.setData(it)
        })
        model.command.observe(viewLifecycleOwner, Observer {
            when (it) {
                Command.GoToCamera -> goToNext(R.id.action_mainFragment_to_cameraFragment)
                Command.GoToLicense -> startActivity(Intent(requireActivity(), OssLicensesMenuActivity::class.java))
                Command.GoToSettings -> goToNext(R.id.action_mainFragment_to_settingsFragment)
                Command.GoToDummy -> goToNext(R.id.action_mainFragment_to_dummyFragment)
            }
            model.command.postValue(null)
        })

        model.init()
    }

    private fun goToNext(resId: Int) {
        Navigation.findNavController(binding.root).navigate(resId)
    }
}
