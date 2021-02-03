package com.erill.roger.feature.gamedetail.domain

import arrow.core.Either
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import io.reactivex.Single

interface GameRepository {
    fun getBestRun(gameId: String): Single<Either<Throwable, GameRun>>
}