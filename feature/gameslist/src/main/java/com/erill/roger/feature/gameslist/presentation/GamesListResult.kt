package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.feature.gameslist.entities.Game

sealed class GamesListResult {
    data class GamesLoaded(val gameList: List<Game>) : GamesListResult()
    object Loading : GamesListResult()
    object Error : GamesListResult()
    object Idle : GamesListResult()
}