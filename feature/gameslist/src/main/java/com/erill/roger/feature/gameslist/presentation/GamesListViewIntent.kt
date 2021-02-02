package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.feature.gameslist.entities.Game

sealed class GamesListViewIntent {

    object InitialLoad : GamesListViewIntent()
    data class GameClicked(val game: Game) : GamesListViewIntent()
    object Refresh : GamesListViewIntent()
}