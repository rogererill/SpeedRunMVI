package com.erill.roger.feature.gameslist.data.remote

import arrow.core.Either
import com.erill.roger.feature.gameslist.data.entities.GameApiEntity
import com.erill.roger.feature.gameslist.data.entities.GameAssetsApiEntity
import com.erill.roger.feature.gameslist.data.entities.GameListApiEntity
import com.erill.roger.feature.gameslist.data.entities.GameNamesApiEntity
import com.erill.roger.feature.gameslist.data.entities.ImageApiEntity
import com.erill.roger.feature.gameslist.entities.Game
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Test
import retrofit2.Response

class GamesRemoteSourceTest {
    private val gameListApiEntity = GameListApiEntity(
        listOf(
            GameApiEntity(
                "1",
                GameNamesApiEntity("game"),
                GameAssetsApiEntity(ImageApiEntity("uri"))
            )
        )
    )
    private val apiRest: RestApi = mock {
        on { getGames() } doReturn Single.just(Response.success(gameListApiEntity))
    }

    private val remoteSource = GamesRemoteSource(apiRest)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(apiRest)
    }

    @Test
    fun testGetGamesFromApi() {
        remoteSource.getGames()
            .test()
            .assertValue(Either.right(listOf(Game("1", "game", "uri"))))

        verify(apiRest).getGames()
    }

    @Test
    fun testGetGamesFromApiKo() {
        whenever(apiRest.getGames()).thenReturn(Single.just(Response.error(404, "".toResponseBody())))
        val testObserver = remoteSource.getGames()
            .test()
        testObserver.assertComplete()

        assert(testObserver.values().first().isLeft())

        verify(apiRest).getGames()
    }
}