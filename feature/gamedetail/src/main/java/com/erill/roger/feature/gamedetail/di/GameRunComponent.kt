package com.erill.roger.feature.gamedetail.di

import com.erill.roger.commons.CommonsComponent
import com.erill.roger.feature.gamedetail.presentation.GameRunActivity
import com.erill.roger.remote.RemoteComponent
import dagger.Component

@Component(
    dependencies = [RemoteComponent::class, CommonsComponent::class],
    modules = [GameRunModule::class]
)
interface GameRunComponent {
    @Component.Builder
    interface Builder {

        fun with(component: RemoteComponent): Builder

        fun with(component: CommonsComponent): Builder

        fun build(): GameRunComponent
    }

    fun gameRunActivityBuilder(): GameRunActivity.Component.Builder
}