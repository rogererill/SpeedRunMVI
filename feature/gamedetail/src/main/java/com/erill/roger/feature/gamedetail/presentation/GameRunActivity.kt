package com.erill.roger.feature.gamedetail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.erill.roger.commons.entities.Game
import com.erill.roger.feature.gamedetail.databinding.ActivityGameDetailBinding
import com.erill.roger.feature.gamedetail.di.GameRunComponentProvider
import dagger.Subcomponent

class GameRunActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_GAME = "EXTRA_GAME"

        fun getCallingIntent(context: Context, game: Game): Intent {
            val intent = Intent(context, GameRunActivity::class.java)
            intent.putExtra(EXTRA_GAME, GameDTO(game))
            return intent
        }
    }

    private lateinit var binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val game = intent.getParcelableExtra<GameDTO>(EXTRA_GAME)!!.toGameRun()

        (applicationContext as GameRunComponentProvider).gameRunComponent
            .gameRunActivityBuilder()
            .build()
            .inject(this)

        binding.tvGameName.text = game.name
        Glide.with(this)
            .load(game.logoUrl)
            .centerCrop()
            .placeholder(android.R.color.darker_gray)
            .into(binding.ivGame)
    }

    @Subcomponent
    interface Component {
        @Subcomponent.Builder
        interface Builder {

            fun build(): Component
        }

        fun inject(activity: GameRunActivity)
    }
}