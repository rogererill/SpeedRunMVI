package com.erill.roger.feature.gamedetail.presentation

sealed class GameRunViewIntent {

    object InitialLoad : GameRunViewIntent()
    object Refresh : GameRunViewIntent()
}