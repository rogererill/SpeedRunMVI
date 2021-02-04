package com.erill.roger.speedrun.navigation

import androidx.appcompat.app.AppCompatActivity
import com.erill.roger.commons.entities.Game
import com.erill.roger.feature.gamedetail.presentation.GameRunActivity
import com.erill.roger.feature.gameslist.presentation.navigator.GamesListOutNavigator
import javax.inject.Inject

class GameListOutNavigatorImpl(
    private val activity: AppCompatActivity
) : GamesListOutNavigator {

    override fun goToGameDetail(game: Game) {
        activity.startActivity(GameRunActivity.getCallingIntent(activity, game))
    }

    class Factory @Inject constructor() : GamesListOutNavigator.Factory {

        override fun create(activity: AppCompatActivity): GamesListOutNavigator {
            return GameListOutNavigatorImpl(activity)
        }
    }
}