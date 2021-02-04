package com.erill.roger.speedrun

import android.app.Application
import com.erill.roger.feature.gamedetail.di.GameRunComponent
import com.erill.roger.feature.gamedetail.di.GameRunComponentProvider
import com.erill.roger.feature.gameslist.di.GamesListComponent
import com.erill.roger.feature.gameslist.di.GamesListComponentProvider

class App : Application(), GamesListComponentProvider, GameRunComponentProvider {

    private lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager()
    }

    override val gamesListComponent: GamesListComponent
        get() = componentManager.gamesListComponent

    override val gameRunComponent: GameRunComponent
        get() = componentManager.gameRunComponent
}