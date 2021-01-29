package com.erill.roger.speedrun

import android.app.Application

class App : Application() {

    private lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager()
    }
}