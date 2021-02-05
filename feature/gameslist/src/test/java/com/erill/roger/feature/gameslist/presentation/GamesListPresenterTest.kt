package com.erill.roger.feature.gameslist.presentation

import androidx.lifecycle.LifecycleOwner
import arrow.core.Either
import com.erill.roger.commons.entities.Game
import com.erill.roger.feature.gameslist.domain.GetGamesUseCase
import com.erill.roger.feature.gameslist.presentation.navigator.GamesListOutNavigator
import com.jakewharton.rxrelay2.PublishRelay
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class GamesListPresenterTest {

    private val eventsObs = PublishRelay.create<GamesListViewIntent>()
    private val view: GamesListView = mock {
        on { userIntents } doReturn eventsObs
    }
    private val response: Either<Throwable, List<Game>> = Either.right(emptyList())
    private val useCase: GetGamesUseCase = mock {
        on { getGames() } doReturn Single.just(response)
    }

    private val owner: LifecycleOwner = mock()
    private val navigator: GamesListOutNavigator = mock()
    private val presenter = GamesListPresenter(view, GamesListProcessor(useCase, Schedulers.trampoline(), Schedulers.trampoline()), navigator)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, useCase, navigator)
    }

    @Test
    fun testInitialLoad() {
        presenter.onCreate(owner)

        verify(view).userIntents
        verify(view).render(GamesListState.Loading)
        verify(view).render(GamesListState.Data(emptyList()))
        verify(useCase).getGames()
    }

    @Test
    fun testRefresh() {
        presenter.onCreate(owner)

        eventsObs.accept(GamesListViewIntent.Refresh)

        verify(view).userIntents
        verify(view, times(2)).render(GamesListState.Loading)
        verify(view, times(2)).render(GamesListState.Data(emptyList()))
        verify(useCase, times(2)).getGames()
    }

    @Test
    fun testNavigate() {
        presenter.onCreate(owner)

        val game = Game("1", "name", "logourl")
        eventsObs.accept(GamesListViewIntent.GameClicked(game))

        verify(view).userIntents
        verify(view).render(GamesListState.Loading)
        verify(view).render(GamesListState.Data(emptyList()))
        verify(useCase).getGames()
        verify(navigator).goToGameDetail(game)
    }
}