package com.erill.roger.feature.gameslist.domain

import arrow.core.Either
import com.erill.roger.commons.OpenClass
import com.erill.roger.feature.gameslist.entities.Game
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class GetGamesUseCase @Inject constructor(private val repository: Repository) {

    fun getGames(): Single<Either<Throwable, List<Game>>> {
        return repository.getGames()
    }
}