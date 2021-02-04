package com.erill.roger.feature.gameslist.data.datasource

import arrow.core.Either
import com.erill.roger.feature.gameslist.data.remote.GamesRemoteSource
import com.erill.roger.feature.gameslist.domain.Repository
import com.erill.roger.commons.entities.Game
import io.reactivex.Single
import javax.inject.Inject

class GamesDataSource @Inject constructor(
    private val remoteSource: GamesRemoteSource
) : Repository {

    override fun getGames(): Single<Either<Throwable, List<Game>>> {
        return remoteSource.getGames()
    }
}