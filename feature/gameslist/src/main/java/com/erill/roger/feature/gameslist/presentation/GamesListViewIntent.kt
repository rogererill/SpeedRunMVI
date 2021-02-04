package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.commons.entities.Game

sealed class GamesListViewIntent {

    object InitialLoad : GamesListViewIntent()
    data class GameClicked(val game: Game) : GamesListViewIntent()
    object Refresh : GamesListViewIntent()
}