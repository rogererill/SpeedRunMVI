package com.erill.roger.feature.gameslist.presentation

sealed class GamesListAction {
    object LoadData : GamesListAction()
    object Navigate : GamesListAction()
}