package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.commons.entities.Game

sealed class GamesListAction {
    object LoadData : GamesListAction()
    data class Navigate(val game: Game) : GamesListAction()
}