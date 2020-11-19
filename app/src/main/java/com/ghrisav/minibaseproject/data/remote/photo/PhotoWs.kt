package com.ghrisav.minibaseproject.data.remote.photo

import com.ghrisav.minibaseproject.data.remote.photo.dto.AlbumDto
import com.ghrisav.minibaseproject.data.remote.photo.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoWs {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumDto>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: Long): AlbumDto

    @GET("photos")
    suspend fun getPhotos(): List<PhotoDto>

    @GET("photos/{id}")
    suspend fun getPhoto(@Path("id") id: Long): PhotoDto
}