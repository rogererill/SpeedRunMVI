package com.erill.roger.feature.gamedetail.presentation

import com.erill.roger.commons.OpenClass
import com.erill.roger.commons.mvi.MviProcessor
import com.erill.roger.feature.gamedetail.domain.usecases.GetBestRunUseCase
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@OpenClass
class GameRunProcessor @Inject constructor(
    private val useCase: GetBestRunUseCase,
    @Named("gameId") private val gameId: String,
    @Named("io") private val io: Scheduler,
    @Named("main") private val main: Scheduler,
) : MviProcessor<GameRunAction, GameRunResult> {

    private val loadDataAction = FlowableTransformer<GameRunAction, GameRunResult> { actions ->
        actions
            .flatMap {
                useCase.getBestRun(gameId)
                    .toFlowable()
                    .map { either ->
                        either.fold(
                            { GameRunResult.Error },
                            { GameRunResult.GameRunLoaded(it) }
                        )
                    }
                    .subscribeOn(io)
                    .observeOn(main)
                    .startWith(GameRunResult.Loading)
            }
    }

    override fun processAction(): FlowableTransformer<GameRunAction, GameRunResult> {
        return FlowableTransformer<GameRunAction, GameRunResult> { actions ->
            actions.publish { flowableActions ->
                flowableActions.ofType(GameRunAction.LoadData::class.java).compose(loadDataAction)
            }
        }
    }
}