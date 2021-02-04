package com.erill.roger.speedrun

import com.erill.roger.commons.CommonsComponent
import com.erill.roger.commons.DaggerCommonsComponentImpl
import com.erill.roger.feature.gamedetail.di.DaggerGameRunComponent
import com.erill.roger.feature.gamedetail.di.GameRunComponent
import com.erill.roger.feature.gameslist.di.DaggerGamesListComponent
import com.erill.roger.feature.gameslist.di.GamesListComponent
import com.erill.roger.remote.DaggerRemoteComponentImpl
import com.erill.roger.remote.RemoteComponent
import com.erill.roger.speedrun.navigation.DaggerNavigationComponent
import com.erill.roger.speedrun.navigation.NavigationComponent

class ComponentManager {

    private val remoteComponent: RemoteComponent by lazy {
        DaggerRemoteComponentImpl.builder().build()
    }

    private val commonsComponent: CommonsComponent by lazy {
        DaggerCommonsComponentImpl.builder().build()
    }

    private val navigationComponent: NavigationComponent by lazy {
        DaggerNavigationComponent.builder()
            .build()
    }

    val gamesListComponent: GamesListComponent by lazy {
        DaggerGamesListComponent
            .builder()
            .with(remoteComponent)
            .with(commonsComponent)
            .with(navigationComponent)
            .build()
    }

    val gameRunComponent: GameRunComponent by lazy {
        DaggerGameRunComponent
            .builder()
            .with(remoteComponent)
            .with(commonsComponent)
            .build()
    }
}