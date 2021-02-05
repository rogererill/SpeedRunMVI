package com.erill.roger.feature.gamedetail.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.erill.roger.commons.entities.Game
import com.erill.roger.feature.gamedetail.databinding.ActivityGameDetailBinding
import com.erill.roger.feature.gamedetail.di.GameRunComponentProvider
import dagger.BindsInstance
import dagger.Subcomponent
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class GameRunActivity : AppCompatActivity(), GameRunView {

    companion object {
        private const val EXTRA_GAME = "EXTRA_GAME"

        fun getCallingIntent(context: Context, game: Game): Intent {
            val intent = Intent(context, GameRunActivity::class.java)
            intent.putExtra(EXTRA_GAME, GameDTO(game))
            return intent
        }
    }

    private lateinit var binding: ActivityGameDetailBinding

    @Inject
    lateinit var presenter: GameRunPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val game = intent.getParcelableExtra<GameDTO>(EXTRA_GAME)!!.toGameRun()

        (applicationContext as GameRunComponentProvider).gameRunComponent
            .gameRunActivityBuilder()
            .withView(this)
            .withGameId(game.id)
            .build()
            .inject(this)

        binding.tvGameName.text = game.name
        Glide.with(this)
            .load(game.logoUrl)
            .centerCrop()
            .placeholder(android.R.color.darker_gray)
            .into(binding.ivGame)

        lifecycle.addObserver(presenter)
    }

    override val userIntents: Observable<GameRunViewIntent>
        get() = Observable.empty() // TODO: Implement refresh

    override fun render(state: GameRunState) {
        when (state) {
            GameRunState.Loading -> {
                binding.progress.visibility = View.VISIBLE
                binding.buttonSeeVideo.isEnabled = false
            }
            GameRunState.Error -> {
                binding.progress.visibility = View.GONE
                binding.buttonSeeVideo.isEnabled = false
                binding.tvPlayerName.text = "Unknown"
                binding.tvTime.text = "Unknown"
            }
            is GameRunState.Data -> {
                binding.progress.visibility = View.GONE
                binding.tvPlayerName.text = state.gameRun.playerName ?: ""
                binding.tvTime.text = DateUtils.formatElapsedTime(state.gameRun.timeInSeconds.toLong())
                binding.buttonSeeVideo.isEnabled = true
                binding.buttonSeeVideo.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(state.gameRun.videoUrl)
                    startActivity(intent)
                }
            }
        }
    }

    @Subcomponent
    interface Component {
        @Subcomponent.Builder
        interface Builder {

            @BindsInstance
            fun withView(view: GameRunView): Builder

            @BindsInstance
            fun withGameId(@Named("gameId") gameId: String): Builder

            fun build(): Component
        }

        fun inject(activity: GameRunActivity)
    }
}