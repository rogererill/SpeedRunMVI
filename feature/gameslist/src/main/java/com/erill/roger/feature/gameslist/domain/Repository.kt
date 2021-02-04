package com.erill.roger.feature.gameslist.domain

import arrow.core.Either
import com.erill.roger.commons.entities.Game
import io.reactivex.Single

interface Repository {

    fun getGames(): Single<Either<Throwable, List<Game>>>
}