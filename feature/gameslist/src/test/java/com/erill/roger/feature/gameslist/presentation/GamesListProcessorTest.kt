package com.erill.roger.feature.gameslist.presentation

import arrow.core.Either
import com.erill.roger.feature.gameslist.domain.GetGamesUseCase
import com.erill.roger.commons.entities.Game
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class GamesListProcessorTest {

    private val response: Either<Throwable, List<Game>> = Either.right(emptyList())
    private val useCase: GetGamesUseCase = mock {
        on { getGames() } doReturn Single.just(response)
    }

    private val processor = GamesListProcessor(useCase, Schedulers.trampoline(), Schedulers.trampoline())

    @After
    fun tearDown() {
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun testGetGamesOk() {
        val flowableLoadData = Flowable.just(GamesListAction.LoadData)
        flowableLoadData
            .compose(processor.processAction())
            .test()
            .assertValues(
                GamesListResult.Loading,
                GamesListResult.GamesLoaded(emptyList())
            )

        verify(useCase).getGames()
    }

    @Test
    fun testGetGamesKO() {
        whenever(useCase.getGames()).thenReturn(Single.just(Either.left(RuntimeException("error"))))
        val flowableLoadData = Flowable.just(GamesListAction.LoadData)
        flowableLoadData
            .compose(processor.processAction())
            .test()
            .assertValues(
                GamesListResult.Loading,
                GamesListResult.Error
            )

        verify(useCase).getGames()
    }

    @Test
    fun testIdleResult() {
        val flowableLoadData = Flowable.just(GamesListAction.Navigate)
        flowableLoadData
            .compose(processor.processAction())
            .test()
            .assertValue(GamesListResult.Idle)
    }
}