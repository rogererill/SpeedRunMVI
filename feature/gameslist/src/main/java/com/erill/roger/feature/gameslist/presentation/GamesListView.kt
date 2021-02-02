package com.erill.roger.feature.gameslist.presentation

import io.reactivex.Observable

interface GamesListView {

    val userIntents: Observable<GamesListViewIntent>

    fun render(state: GamesListState)
}