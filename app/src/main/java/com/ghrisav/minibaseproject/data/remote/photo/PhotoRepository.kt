package com.ghrisav.minibaseproject.data.remote.photo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PhotoRepository(private val photoWs: PhotoWs) {

    fun getAlbums() = flow {
        emit(photoWs.getAlbums().toBo())
    }.flowOn(Dispatchers.IO)

    fun getAlbum(id: Long) = flow {
        emit(photoWs.getAlbum(id).toBo())
    }.flowOn(Dispatchers.IO)

    fun getPhotos() = flow {
        emit(photoWs.getPhotos().toBo())
    }.flowOn(Dispatchers.IO)

    fun getPhoto(id: Long) = flow {
        emit(photoWs.getPhoto(id).toBo())
    }.flowOn(Dispatchers.IO)
}