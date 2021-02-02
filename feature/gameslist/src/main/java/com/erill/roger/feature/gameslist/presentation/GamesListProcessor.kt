package com.erill.roger.feature.gameslist.presentation

import com.erill.roger.commons.mvi.MviProcessor
import com.erill.roger.feature.gameslist.domain.GetGamesUseCase
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GamesListProcessor @Inject constructor(private val useCase: GetGamesUseCase) : MviProcessor<GamesListAction, GamesListResult> {

    private val loadDataAction = FlowableTransformer<GamesListAction, GamesListResult> { actions ->
        actions
            .flatMap {
                useCase.getGames()
                    .toFlowable()
                    .map { either ->
                        either.fold(
                            { GamesListResult.Error },
                            { GamesListResult.GamesLoaded(it) }
                        )
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(GamesListResult.Loading)
            }
    }

    private val idleAction = FlowableTransformer<GamesListAction, GamesListResult> { actions ->
        actions.map { GamesListResult.Idle }
    }

    override fun processAction(): FlowableTransformer<GamesListAction, GamesListResult> {
        return FlowableTransformer<GamesListAction, GamesListResult> { actions ->
            actions.publish { flowableActions ->
                Flowable.merge(
                    flowableActions.ofType(GamesListAction.LoadData::class.java).compose(loadDataAction),
                    flowableActions.ofType(GamesListAction.Navigate::class.java).compose(idleAction)
                )
            }
        }
    }
}