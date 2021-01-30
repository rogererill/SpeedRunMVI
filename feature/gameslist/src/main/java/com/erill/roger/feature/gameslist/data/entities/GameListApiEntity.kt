package com.erill.roger.feature.gameslist.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameListApiEntity(
    val data: List<GameApiEntity>
)

@JsonClass(generateAdapter = true)
data class GameApiEntity(
    val id: String,
    val names: GameNamesApiEntity,
    val assets: GameAssetsApiEntity
)

@JsonClass(generateAdapter = true)
data class GameNamesApiEntity(
    @Json(name = "international") val name: String
)

@JsonClass(generateAdapter = true)
data class GameAssetsApiEntity(
    @Json(name = "cover-medium") val image: ImageApiEntity
)

@JsonClass(generateAdapter = true)
data class ImageApiEntity(
    val uri: String
)