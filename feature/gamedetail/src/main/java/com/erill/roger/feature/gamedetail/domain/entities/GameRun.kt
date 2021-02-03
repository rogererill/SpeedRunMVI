package com.erill.roger.feature.gamedetail.domain.entities

data class GameRun(
    val id: String,
    val playerId: String?,
    val playerName: String?,
    val videoUrl: String,
    val timeInSeconds: Int
)