package com.erill.roger.speedrun

import android.app.Application
import com.erill.roger.feature.gameslist.di.GamesListComponent
import com.erill.roger.feature.gameslist.di.GamesListComponentProvider

class App : Application(), GamesListComponentProvider {

    private lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager()
    }

    override val gamesListComponent: GamesListComponent
        get() = componentManager.gamesListComponent
}