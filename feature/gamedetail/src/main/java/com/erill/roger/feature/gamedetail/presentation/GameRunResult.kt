package com.erill.roger.feature.gamedetail.presentation

import com.erill.roger.feature.gamedetail.domain.entities.GameRun

sealed class GameRunResult {
    data class GameRunLoaded(val gameRun: GameRun) : GameRunResult()
    object Loading : GameRunResult()
    object Error : GameRunResult()
}