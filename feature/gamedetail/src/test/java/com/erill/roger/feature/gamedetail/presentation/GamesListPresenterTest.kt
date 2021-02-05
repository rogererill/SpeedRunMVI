package com.erill.roger.feature.gamedetail.presentation

import androidx.lifecycle.LifecycleOwner
import arrow.core.Either
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import com.erill.roger.feature.gamedetail.domain.usecases.GetBestRunUseCase
import com.jakewharton.rxrelay2.PublishRelay
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class GameRunPresenterTest {

    private val eventsObs = PublishRelay.create<GameRunViewIntent>()
    private val view: GameRunView = mock {
        on { userIntents } doReturn eventsObs
    }
    private val gameRun = GameRun("1", "2", "name", "url", 123)
    private val response: Either<Throwable, GameRun> = Either.right(gameRun)
    private val useCase: GetBestRunUseCase = mock {
        on { getBestRun(any()) } doReturn Single.just(response)
    }

    private val owner: LifecycleOwner = mock()
    private val presenter = GameRunPresenter(view, GameRunProcessor(useCase, "1", Schedulers.trampoline(), Schedulers.trampoline()))

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, useCase)
    }

    @Test
    fun testInitialLoad() {
        presenter.onCreate(owner)

        verify(view).userIntents
        verify(view).render(GameRunState.Loading)
        verify(view).render(GameRunState.Data(gameRun))
        verify(useCase).getBestRun("1")
    }

    @Test
    fun testRefresh() {
        presenter.onCreate(owner)

        eventsObs.accept(GameRunViewIntent.Refresh)

        verify(view).userIntents
        verify(view, times(2)).render(GameRunState.Loading)
        verify(view, times(2)).render(GameRunState.Data(gameRun))
        verify(useCase, times(2)).getBestRun("1")
    }
}