package com.ghrisav.minibaseproject.features.home.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghrisav.minibaseproject.common.ui.fragment.BaseFragment
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel
import com.ghrisav.minibaseproject.databinding.FragmentHomeBinding
import com.ghrisav.minibaseproject.features.home.ui.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    override fun getViewModel(): BaseViewModel = homeViewModel

    override fun onCreateFragment() {
        super.onCreateFragment()
        homeViewModel.fetchAlbums()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreatedFragment() {
        homeViewModel.getAlbums().observe(viewLifecycleOwner, {
            Log.wtf("Albums", it.toString())
        })
    }
}