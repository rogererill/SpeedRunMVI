package com.erill.roger.feature.gameslist.data.remote

import arrow.core.Either
import com.erill.roger.commons.OpenClass
import com.erill.roger.commons.entities.Game
import com.erill.roger.remote.toGenericEither
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class GamesRemoteSource @Inject constructor(
    private val restApi: RestApi
) {

    fun getGames(): Single<Either<Throwable, List<Game>>> {
        return restApi.getGames()
            .toGenericEither { it.toDomain() }
    }
}