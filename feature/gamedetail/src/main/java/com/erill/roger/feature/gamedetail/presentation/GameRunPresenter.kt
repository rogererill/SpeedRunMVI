package com.erill.roger.feature.gamedetail.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GameRunPresenter @Inject constructor(
    private val view: GameRunView,
    private val processor: GameRunProcessor
) : DefaultLifecycleObserver {

    private val disposable = CompositeDisposable()

    override fun onCreate(owner: LifecycleOwner) {

        val stateFlowable: Flowable<GameRunState> = view.userIntents
            .startWith(GameRunViewIntent.InitialLoad)
            .toFlowable(BackpressureStrategy.BUFFER)
            .map {
                when (it) {
                    is GameRunViewIntent.InitialLoad -> GameRunAction.LoadData
                    is GameRunViewIntent.Refresh -> GameRunAction.LoadData
                }
            }
            .compose(processor.processAction())
            .scan(GameRunState.Loading, reducer)
            .distinctUntilChanged()

        disposable.add(
            stateFlowable
                .subscribe(
                    { view.render(it) },
                    { throw RuntimeException(it) }
                )
        )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        disposable.dispose()
    }

    private val reducer = BiFunction { _: GameRunState, result: GameRunResult ->
        when (result) {
            is GameRunResult.GameRunLoaded -> GameRunState.Data(result.gameRun)
            GameRunResult.Loading -> GameRunState.Loading
            GameRunResult.Error -> GameRunState.Error
        }
    }
}
