package com.erill.roger.feature.gameslist.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.erill.roger.feature.gameslist.databinding.ActivityGameslistBinding
import com.erill.roger.feature.gameslist.di.GamesListComponentProvider
import com.erill.roger.feature.gameslist.presentation.navigator.GameListNavigatorModule
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import dagger.BindsInstance
import dagger.Subcomponent
import io.reactivex.Observable
import javax.inject.Inject

class GamesListActivity : AppCompatActivity(), GamesListView {

    private lateinit var binding: ActivityGameslistBinding

    @Inject
    lateinit var presenter: GamesListPresenter

    private lateinit var adapter: GamesAdapter

    private val eventsRelay: Relay<GamesListViewIntent> = PublishRelay.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameslistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvGamesList.layoutManager = LinearLayoutManager(this)
        binding.rvGamesList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        (applicationContext as GamesListComponentProvider).gamesListComponent
            .gamesListActivityBuilder()
            .withView(this)
            .withActivity(this)
            .build()
            .inject(this)

        adapter = GamesAdapter { eventsRelay.accept(GamesListViewIntent.GameClicked(it)) }
        binding.rvGamesList.adapter = adapter

        lifecycle.addObserver(presenter)
    }

    override val userIntents: Observable<GamesListViewIntent>
        get() = eventsRelay

    override fun render(state: GamesListState) {
        return when (state) {
            GamesListState.Loading -> {
                binding.progress.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
            }
            GamesListState.Error -> {
                binding.progress.visibility = View.GONE
                binding.rvGamesList.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
            }
            is GamesListState.Data -> {
                binding.progress.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.rvGamesList.visibility = View.VISIBLE
                adapter.data = state.games
            }
        }
    }

    @Subcomponent(modules = [GameListNavigatorModule::class])
    interface Component {
        @Subcomponent.Builder
        interface Builder {

            @BindsInstance
            fun withView(view: GamesListView): Builder

            @BindsInstance
            fun withActivity(activity: AppCompatActivity): Builder

            fun build(): Component
        }

        fun inject(activity: GamesListActivity)
    }
}