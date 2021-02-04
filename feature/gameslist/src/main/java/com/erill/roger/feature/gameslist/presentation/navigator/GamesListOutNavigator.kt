package com.erill.roger.feature.gameslist.presentation.navigator

import androidx.appcompat.app.AppCompatActivity
import com.erill.roger.commons.entities.Game

interface GamesListOutNavigator {

    fun goToGameDetail(game: Game)

    interface Factory {
        fun create(activity: AppCompatActivity): GamesListOutNavigator
    }
}