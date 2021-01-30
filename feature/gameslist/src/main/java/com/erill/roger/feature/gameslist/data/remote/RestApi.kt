package com.erill.roger.feature.gameslist.data.remote

import com.erill.roger.feature.gameslist.data.entities.GameListApiEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET("games")
    fun getGames(): Single<Response<GameListApiEntity>>
}