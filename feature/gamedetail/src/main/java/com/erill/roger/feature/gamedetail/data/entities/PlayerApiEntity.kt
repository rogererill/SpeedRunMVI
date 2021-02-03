package com.erill.roger.feature.gamedetail.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerApiEntity(
    val data: PlayerDataApiEntity
)

@JsonClass(generateAdapter = true)
data class PlayerDataApiEntity(
    val names: PlayerNamesApiEntity,
)

@JsonClass(generateAdapter = true)
data class PlayerNamesApiEntity(
    @Json(name = "international") val name: String?
)