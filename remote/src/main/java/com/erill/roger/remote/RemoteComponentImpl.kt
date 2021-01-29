package com.erill.roger.remote

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class])
interface RemoteComponentImpl : RemoteComponent {

    @Component.Builder
    interface Builder {
        fun build(): RemoteComponentImpl
    }
}