package com.erill.roger.feature.gamedetail.data.datasource

import arrow.core.Either
import com.erill.roger.feature.gamedetail.data.remote.GameRunRemoteSource
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.After
import org.junit.Test

class GameDataSourceTest {
    private val gameRun = GameRun("1", null, "name", "url", 123)
    private val eitherGameRun: Either<Throwable, GameRun> = Either.right(gameRun)
    private val remoteSource: GameRunRemoteSource = mock {
        on { getBestRun(any()) } doReturn Single.just(eitherGameRun)
    }
    private val dataRepository = GameDataSource(remoteSource)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(remoteSource)
    }

    @Test
    fun testGetBestRunDataRepository() {
        dataRepository.getBestRun("1")
            .test()
            .assertValue(eitherGameRun)

        verify(remoteSource).getBestRun("1")
    }
}