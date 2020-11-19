package com.ghrisav.minibaseproject.data.remote.photo

import com.ghrisav.minibaseproject.data.model.AlbumBo
import com.ghrisav.minibaseproject.data.model.PhotoBo
import com.ghrisav.minibaseproject.data.remote.photo.dto.AlbumDto
import com.ghrisav.minibaseproject.data.remote.photo.dto.PhotoDto

fun AlbumDto.toBo() = AlbumBo(userId, id, title)

fun PhotoDto.toBo() = PhotoBo(albumId, id, title, url, thumbnailUrl)

@JvmName("AlbumDtoListToBo")
fun List<AlbumDto>.toBo() = map { it.toBo() }

@JvmName("PhotoDtoListToBo")
fun List<PhotoDto>.toBo() = map { it.toBo() }