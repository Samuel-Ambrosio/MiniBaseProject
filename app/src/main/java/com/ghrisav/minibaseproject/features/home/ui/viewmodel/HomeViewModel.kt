package com.ghrisav.minibaseproject.features.home.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ghrisav.minibaseproject.common.extensions.execute
import com.ghrisav.minibaseproject.common.extensions.toLiveData
import com.ghrisav.minibaseproject.common.ui.viewmodel.BaseViewModel
import com.ghrisav.minibaseproject.data.model.AlbumBo
import com.ghrisav.minibaseproject.data.remote.photo.PhotoRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val photoRepository: PhotoRepository) : BaseViewModel() {

    private val albums = MutableLiveData<List<AlbumBo>>()
    fun getAlbums() = albums.toLiveData()

    fun fetchAlbums() = viewModelScope.launch {
        photoRepository.getAlbums().execute(albums, fullScreenLoading, snackBarError)
    }
}