package com.ghrisav.minibaseproject.data.remote.photo.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("albumId")
    val albumId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)