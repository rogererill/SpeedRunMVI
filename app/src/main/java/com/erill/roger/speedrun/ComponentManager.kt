package com.erill.roger.speedrun

import com.erill.roger.remote.DaggerRemoteComponentImpl
import com.erill.roger.remote.RemoteComponent

class ComponentManager {

    private val remoteComponent: RemoteComponent by lazy {
        DaggerRemoteComponentImpl.builder().build()
    }
}