package com.erill.roger.commons

import dagger.Component

@Component(modules = [ActivityModule::class])
interface CommonsComponentImpl : CommonsComponent {

    @Component.Builder
    interface Builder {
        fun build(): CommonsComponentImpl
    }
}