package com.erill.roger.feature.gameslist.di

import com.erill.roger.remote.RemoteComponent
import dagger.Component

@Component(
    dependencies = [RemoteComponent::class],
    modules = [GamesListModule::class]
)
interface GamesListComponent {
    @Component.Builder
    interface Builder {

        fun with(component: RemoteComponent): Builder

        fun build(): GamesListComponent
    }
}