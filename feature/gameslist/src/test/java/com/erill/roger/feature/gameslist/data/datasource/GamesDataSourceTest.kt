package com.erill.roger.feature.gameslist.data.datasource

import arrow.core.Either
import com.erill.roger.feature.gameslist.data.remote.GamesRemoteSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Test

class GamesDataSourceTest {

    private val remoteSource: GamesRemoteSource = mock {
        on { getGames() } doReturn Single.just(Either.right(listOf()))
    }
    private val dataRepository = GamesDataSource(remoteSource)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(remoteSource)
    }

    @Test
    fun testGetGamesDataRepositoryOk() {
        dataRepository.getGames()
            .test()
            .assertValue(Either.right(listOf()))

        verify(remoteSource).getGames()
    }

    @Test
    fun testGetGamesDataRepositoryKO() {
        val throwable = Throwable()
        whenever(remoteSource.getGames()).thenReturn(Single.just(Either.left(throwable)))
        dataRepository.getGames()
            .test()
            .assertValue(Either.left(throwable))

        verify(remoteSource).getGames()
    }
}