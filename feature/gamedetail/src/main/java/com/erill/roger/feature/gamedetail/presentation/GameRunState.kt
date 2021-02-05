package com.erill.roger.feature.gamedetail.presentation

import com.erill.roger.feature.gamedetail.domain.entities.GameRun

sealed class GameRunState {
    object Loading : GameRunState()
    object Error : GameRunState()
    data class Data(val gameRun: GameRun) : GameRunState()
}