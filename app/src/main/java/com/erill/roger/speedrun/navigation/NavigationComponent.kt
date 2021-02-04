package com.erill.roger.speedrun.navigation

import com.erill.roger.feature.gameslist.presentation.navigator.GameListNavigatorComponent
import dagger.Component

@Component(
    modules = [NavigationModule::class]
)
interface NavigationComponent : GameListNavigatorComponent