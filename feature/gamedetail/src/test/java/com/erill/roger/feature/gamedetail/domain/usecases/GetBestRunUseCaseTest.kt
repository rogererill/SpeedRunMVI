package com.erill.roger.feature.gamedetail.domain.usecases

import arrow.core.Either
import com.erill.roger.feature.gamedetail.domain.GameRepository
import com.erill.roger.feature.gamedetail.domain.entities.GameRun
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.After
import org.junit.Test

class GetBestRunUseCaseTest {

    private val response = GameRun("1", "2", "name", "url", 10)
    private val eitherResponse: Either<Throwable, GameRun> = Either.right(response)

    private val repository: GameRepository = mock {
        on { getBestRun(any()) } doReturn Single.just(eitherResponse)
    }

    private val useCase: GetBestRunUseCase = GetBestRunUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testGetBestRun() {
        useCase.getBestRun("1")
            .test()
            .assertValue(eitherResponse)

        verify(repository).getBestRun("1")
    }
}