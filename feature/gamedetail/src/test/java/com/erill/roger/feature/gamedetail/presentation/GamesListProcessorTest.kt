package com.erill.roger.feature.gamedetail.presentation

import arrow.core.Either
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import com.erill.roger.feature.gamedetail.domain.usecases.GetBestRunUseCase
import com.nhaarman.mockitokotlin2.any
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

class GameRunProcessorTest {

    private val gameRun = GameRun("1", "2", "name", "url", 123)
    private val response: Either<Throwable, GameRun> = Either.right(gameRun)
    private val useCase: GetBestRunUseCase = mock {
        on { getBestRun(any()) } doReturn Single.just(response)
    }

    private val processor = GameRunProcessor(useCase, "1", Schedulers.trampoline(), Schedulers.trampoline())

    @After
    fun tearDown() {
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun testGetBestRunOk() {
        val flowableLoadData = Flowable.just(GameRunAction.LoadData)
        flowableLoadData
            .compose(processor.processAction())
            .test()
            .assertValues(
                GameRunResult.Loading,
                GameRunResult.GameRunLoaded(gameRun)
            )

        verify(useCase).getBestRun("1")
    }

    @Test
    fun testGetGamesKO() {
        whenever(useCase.getBestRun(any())).thenReturn(Single.just(Either.left(RuntimeException("error"))))
        val flowableLoadData = Flowable.just(GameRunAction.LoadData)
        flowableLoadData
            .compose(processor.processAction())
            .test()
            .assertValues(
                GameRunResult.Loading,
                GameRunResult.Error
            )

        verify(useCase).getBestRun("1")
    }
}