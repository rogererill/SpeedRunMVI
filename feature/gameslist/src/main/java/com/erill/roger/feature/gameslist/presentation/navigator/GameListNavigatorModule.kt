package com.erill.roger.feature.gameslist.presentation.navigator

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
abstract class GameListNavigatorModule {

    companion object {

        @Provides
        fun mainNavigatorFactoryProvider(activity: AppCompatActivity, factory: GamesListOutNavigator.Factory): GamesListOutNavigator {
            return factory.create(activity)
        }
    }
}