package com.erill.roger.feature.gamedetail.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunListApiEntity(
    val data: List<RunApiEntity>
)

@JsonClass(generateAdapter = true)
data class RunApiEntity(
    val id: String,
    val videos: VideoApiEntity,
    val players: List<PlayerEntity>,
    val times: TimeApiEntity
)

@JsonClass(generateAdapter = true)
data class VideoApiEntity(
    val links: List<VideoLinkApiEntity>
)

@JsonClass(generateAdapter = true)
data class VideoLinkApiEntity(
    @Json(name = "uri") val url: String
)

@JsonClass(generateAdapter = true)
data class PlayerEntity(
    val id: String?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class TimeApiEntity(
    @Json(name = "primary_t") val timeInSeconds: Float
)