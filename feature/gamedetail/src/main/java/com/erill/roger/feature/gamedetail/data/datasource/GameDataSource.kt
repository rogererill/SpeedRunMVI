package com.erill.roger.feature.gamedetail.data.datasource

import arrow.core.Either
import com.erill.roger.feature.gamedetail.data.remote.GameRunRemoteSource
import com.erill.roger.feature.gamedetail.domain.GameRepository
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import io.reactivex.Single
import javax.inject.Inject

class GameDataSource @Inject constructor(
    private val remoteSource: GameRunRemoteSource
) : GameRepository {

    override fun getBestRun(gameId: String): Single<Either<Throwable, GameRun>> {
        return remoteSource.getBestRun(gameId)
    }
}