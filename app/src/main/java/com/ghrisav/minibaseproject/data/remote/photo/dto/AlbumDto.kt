package com.ghrisav.minibaseproject.data.remote.photo.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String
)