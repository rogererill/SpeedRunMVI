package com.erill.roger.feature.gamedetail.presentation

import io.reactivex.Observable

interface GameRunView {

    val userIntents: Observable<GameRunViewIntent>

    fun render(state: GameRunState)
}