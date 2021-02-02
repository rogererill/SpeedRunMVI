package com.erill.roger.feature.gameslist.di

import com.erill.roger.commons.CommonsComponent
import com.erill.roger.feature.gameslist.presentation.GamesListActivity
import com.erill.roger.remote.RemoteComponent
import dagger.Component

@Component(
    dependencies = [RemoteComponent::class, CommonsComponent::class],
    modules = [GamesListModule::class]
)
interface GamesListComponent {
    @Component.Builder
    interface Builder {

        fun with(component: RemoteComponent): Builder

        fun with(component: CommonsComponent): Builder

        fun build(): GamesListComponent
    }

    fun gamesListActivityBuilder(): GamesListActivity.Component.Builder
}