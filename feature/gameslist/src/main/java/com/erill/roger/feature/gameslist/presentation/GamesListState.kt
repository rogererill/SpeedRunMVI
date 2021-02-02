package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.feature.gameslist.entities.Game

sealed class GamesListState {
    object Loading : GamesListState()
    object Error : GamesListState()
    data class Data(val games: List<Game>) : GamesListState()
}