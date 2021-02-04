package com.erill.roger.speedrun.navigation

import com.erill.roger.feature.gameslist.presentation.navigator.GamesListOutNavigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {

    @Binds
    abstract fun gameListNavigatorFactoryProvider(factory: GameListOutNavigatorImpl.Factory): GamesListOutNavigator.Factory
}