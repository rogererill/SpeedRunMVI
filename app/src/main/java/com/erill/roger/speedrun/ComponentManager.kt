package com.erill.roger.speedrun

import com.erill.roger.commons.CommonsComponent
import com.erill.roger.commons.DaggerCommonsComponentImpl
import com.erill.roger.feature.gameslist.di.DaggerGamesListComponent
import com.erill.roger.feature.gameslist.di.GamesListComponent
import com.erill.roger.remote.DaggerRemoteComponentImpl
import com.erill.roger.remote.RemoteComponent

class ComponentManager {

    private val remoteComponent: RemoteComponent by lazy {
        DaggerRemoteComponentImpl.builder().build()
    }

    private val commonsComponent: CommonsComponent by lazy {
        DaggerCommonsComponentImpl.builder().build()
    }

    val gamesListComponent: GamesListComponent by lazy {
        DaggerGamesListComponent
            .builder()
            .with(remoteComponent)
            .with(commonsComponent)
            .build()
    }
}