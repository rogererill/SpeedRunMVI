package com.erill.roger.feature.gameslist.data.remote

import com.erill.roger.feature.gameslist.data.entities.GameListApiEntity
import com.erill.roger.commons.entities.Game

fun GameListApiEntity.toDomain(): List<Game> {
    return data.map {
        Game(it.id, it.names.name, it.assets.image.uri)
    }
}