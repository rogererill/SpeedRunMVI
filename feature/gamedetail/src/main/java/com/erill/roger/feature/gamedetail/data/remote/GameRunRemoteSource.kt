package com.erill.roger.feature.gamedetail.data.remote

import arrow.core.Either
import com.erill.roger.commons.OpenClass
import com.erill.roger.feature.gamedetail.data.entities.RunApiEntity
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import io.reactivex.Single
import javax.inject.Inject
import kotlin.math.roundToInt

@OpenClass
class GameRunRemoteSource @Inject constructor(
    private val restApi: GameRunRestApi
) {

    fun getBestRun(gameId: String): Single<Either<Throwable, GameRun>> {
        return restApi.getRuns(gameId)
            .map { response ->
                if (response.isSuccessful) {
                    val gameRunsApiEntity = response.body()!!
                    val data: List<RunApiEntity> = gameRunsApiEntity.data
                    val gamesRunList = data.map { runApiEntity ->
                        val player = runApiEntity.players.firstOrNull()
                        GameRun(
                            runApiEntity.id,
                            player?.id,
                            player?.name,
                            runApiEntity.videos.links.first().url,
                            runApiEntity.times.timeInSeconds.roundToInt()
                        )
                    }
                    val gameRun: GameRun? = gamesRunList.minByOrNull { it.timeInSeconds }
                    if (gameRun != null) {
                        Either.right(gameRun)
                    } else {
                        Either.left(RuntimeException("No runs to show for game $gameId"))
                    }
                } else {
                    Either.left(RuntimeException("Error in response"))
                }
            }
            .flatMap { either ->
                either.fold(
                    { Single.just(Either.left(it)) },
                    {
                        if (it.playerName == null) {
                            getPlayerName(it.playerId!!)
                                .map { name ->
                                    val gameRun = it.copy(playerName = name)
                                    Either.right(gameRun)
                                }
                        } else {
                            Single.just(Either.right(it))
                        }
                    }
                )
            }
    }

    private fun getPlayerName(playerId: String): Single<String> {
        return restApi.getPlayerName(playerId)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()!!.data.names.name ?: ""
                } else {
                    ""
                }
            }
    }
}