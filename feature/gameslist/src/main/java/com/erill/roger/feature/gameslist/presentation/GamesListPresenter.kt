package com.erill.roger.feature.gameslist.presentation

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GamesListPresenter @Inject constructor(
    private val view: GamesListView,
    private val processor: GamesListProcessor
) : DefaultLifecycleObserver {

    private val disposable = CompositeDisposable()

    override fun onCreate(owner: LifecycleOwner) {

        val stateFlowable: Flowable<GamesListState> = view.userIntents
            .startWith(GamesListViewIntent.InitialLoad)
            .toFlowable(BackpressureStrategy.BUFFER)
            .map {
                when (it) {
                    is GamesListViewIntent.InitialLoad -> GamesListAction.LoadData
                    is GamesListViewIntent.GameClicked -> GamesListAction.Navigate
                    is GamesListViewIntent.Refresh -> GamesListAction.LoadData
                }
            }
            .doOnNext {
                when (it) {
                    GamesListAction.Navigate -> Log.d("TESTTAG", "navigate from here to new screen")
                    else -> {
                        /* no-op */
                    }
                }
            }
            .compose(processor.processAction())
            .scan(GamesListState.Loading, reducer)
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

    private val reducer = BiFunction { previousState: GamesListState, result: GamesListResult ->
        when (result) {
            is GamesListResult.GamesLoaded -> GamesListState.Data(result.gameList)
            GamesListResult.Loading -> GamesListState.Loading
            GamesListResult.Error -> GamesListState.Error
            GamesListResult.Idle -> previousState
        }
    }
}
