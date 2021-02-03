package com.erill.roger.feature.gamedetail.domain.usecases

import arrow.core.Either
import com.erill.roger.commons.OpenClass
import com.erill.roger.feature.gamedetail.domain.GameRepository
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class GetBestRunUseCase @Inject constructor(private val repository: GameRepository) {

    fun getBestRun(gameId: String): Single<Either<Throwable, GameRun>> {
        return repository.getBestRun(gameId)
    }
}