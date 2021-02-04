package com.erill.roger.feature.gameslist.presentation.navigator

interface GameListNavigatorComponent {
    fun gameListNavigatorFactoryProvider() : GamesListOutNavigator.Factory
}