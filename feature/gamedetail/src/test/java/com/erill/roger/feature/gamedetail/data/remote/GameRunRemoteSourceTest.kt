package com.erill.roger.feature.gamedetail.data.remote

import arrow.core.Either
import com.erill.roger.feature.gamedetail.data.entities.PlayerApiEntity
import com.erill.roger.feature.gamedetail.data.entities.PlayerDataApiEntity
import com.erill.roger.feature.gamedetail.data.entities.PlayerEntity
import com.erill.roger.feature.gamedetail.data.entities.PlayerNamesApiEntity
import com.erill.roger.feature.gamedetail.data.entities.RunApiEntity
import com.erill.roger.feature.gamedetail.data.entities.RunListApiEntity
import com.erill.roger.feature.gamedetail.data.entities.TimeApiEntity
import com.erill.roger.feature.gamedetail.data.entities.VideoApiEntity
import com.erill.roger.feature.gamedetail.data.entities.VideoLinkApiEntity
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Test
import retrofit2.Response

class GameRunRemoteSourceTest {

    private val runListApiEntity = RunListApiEntity(
        listOf(
            RunApiEntity(
                "1",
                VideoApiEntity(listOf(VideoLinkApiEntity("url"))),
                listOf(PlayerEntity("1", "name")),
                TimeApiEntity(123f)
            )
        )
    )
    private val playerApiEntity = PlayerApiEntity(PlayerDataApiEntity(PlayerNamesApiEntity("Resolved name")))

    private val apiRest: GameRunRestApi = mock {
        on { getRuns(any()) } doReturn Single.just(Response.success(runListApiEntity))
        on { getPlayerName(any()) } doReturn Single.just(Response.success(playerApiEntity))
    }
    private val remoteSource = GameRunRemoteSource(apiRest)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(apiRest)
    }

    @Test
    fun testGetRunsWithPlayer() {
        remoteSource.getBestRun("1")
            .test()
            .assertValue(Either.right(GameRun("1", "1", "name", "url", 123)))

        verify(apiRest).getRuns("1")
    }

    @Test
    fun testGetRunsWithPlayerNoName() {
        val runListApiEntityNoPlayer = RunListApiEntity(
            listOf(
                RunApiEntity(
                    "1",
                    VideoApiEntity(listOf(VideoLinkApiEntity("url"))),
                    listOf(PlayerEntity("2", null)),
                    TimeApiEntity(123f)
                )
            )
        )
        whenever(apiRest.getRuns(any())).thenReturn(Single.just(Response.success(runListApiEntityNoPlayer)))
        remoteSource.getBestRun("1")
            .test()
            .assertValue(Either.right(GameRun("1", "2", "Resolved name", "url", 123)))

        verify(apiRest).getRuns("1")
        verify(apiRest).getPlayerName("2")
    }
}