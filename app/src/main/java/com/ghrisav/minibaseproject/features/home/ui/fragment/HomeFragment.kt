package com.ghrisav.minibaseproject.features.home.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ghrisav.minibaseproject.R
import com.ghrisav.minibaseproject.common.ui.fragment.BaseFragment
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel
import com.ghrisav.minibaseproject.databinding.FragmentHomeBinding
import com.ghrisav.minibaseproject.features.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    ) = FragmentHomeBinding.inflate(inflater, container, false).apply { binding = this }.root

    override fun onViewCreatedFragment() {
        setUpView()
        setUpObservers()
    }

    private fun setUpView() {
        val view = binding.homeCustomView.getViewFromFrontView("test_front__btn")
        binding.homeCustomView.setSwipedLayout(R.layout.test_back_right)
        view?.setOnClickListener {
            binding.homeCustomView.animateSwipe()
        }
    }

    private fun setUpObservers() {
        homeViewModel.getAlbums().observe(viewLifecycleOwner, {
            Log.wtf("Albums", it.toString())
        })
    }
}