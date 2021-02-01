package com.erill.roger.feature.gameslist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.erill.roger.feature.gameslist.databinding.ActivityGameslistBinding
import com.erill.roger.feature.gameslist.di.GamesListComponentProvider
import dagger.Subcomponent

class GamesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameslistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameslistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvGamesList.layoutManager = LinearLayoutManager(this)
        binding.rvGamesList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        (applicationContext as GamesListComponentProvider).gamesListComponent
            .gamesListActivityBuilder()
            .build()
            .inject(this)
    }

    @Subcomponent
    interface Component {
        @Subcomponent.Builder
        interface Builder {

            fun build(): Component
        }

        fun inject(activity: GamesListActivity)
    }
}