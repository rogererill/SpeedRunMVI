package com.erill.roger.feature.gameslist.domain

import arrow.core.Either
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Test

class GetGamesUseCaseTest {
    private val repository: Repository = mock()

    private val usecase = GetGamesUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getGamesOk() {
        whenever(repository.getGames()).thenReturn(Single.just(Either.right(emptyList())))

        usecase.getGames()
            .test()
            .assertValue(Either.right(emptyList()))

        verify(repository).getGames()
    }

    @Test
    fun getGamesKo() {
        val throwable = Throwable()
        whenever(repository.getGames()).thenReturn(Single.just(Either.left(throwable)))

        usecase.getGames()
            .test()
            .assertValue(Either.left(throwable))

        verify(repository).getGames()
    }
}