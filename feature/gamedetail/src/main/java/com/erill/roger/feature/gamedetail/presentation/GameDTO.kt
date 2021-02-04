package com.erill.roger.feature.gamedetail.presentation

import android.os.Parcelable
import com.erill.roger.commons.entities.Game
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDTO(
    val id: String,
    val name: String,
    val logoUrl: String
) : Parcelable {

    constructor(game: Game) : this(game.id, game.name, game.logoUrl)

    fun toGameRun(): Game {
        return Game(id, name, logoUrl)
    }
}