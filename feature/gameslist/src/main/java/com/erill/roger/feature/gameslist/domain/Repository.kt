package com.erill.roger.feature.gameslist.domain

import arrow.core.Either
import com.erill.roger.feature.gameslist.entities.Game
import io.reactivex.Single

interface Repository {

    fun getGames(): Single<Either<Throwable, List<Game>>>
}