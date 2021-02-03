package com.erill.roger.feature.gamedetail.data.remote

import com.erill.roger.feature.gamedetail.data.entities.PlayerApiEntity
import com.erill.roger.feature.gamedetail.data.entities.RunListApiEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameRunRestApi {

    @GET("runs")
    fun getRuns(@Query("game") gameId: String): Single<Response<RunListApiEntity>>

    @GET("users/{player_id}")
    fun getPlayerName(@Path("player_id") playerId: String): Single<Response<PlayerApiEntity>>
}