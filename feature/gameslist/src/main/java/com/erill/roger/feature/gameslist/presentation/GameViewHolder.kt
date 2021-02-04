package com.erill.roger.feature.gameslist.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erill.roger.feature.gameslist.R
import com.erill.roger.commons.entities.Game

class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val gameImage: ImageView = view.findViewById(R.id.iv_logo)
    private val gameName: TextView = view.findViewById(R.id.tv_game_name)

    fun bind(game: Game, clickListener: (Game) -> Unit) {
        itemView.setOnClickListener { clickListener(game) }
        gameName.text = game.name
        Glide.with(itemView.context)
            .load(game.logoUrl)
            .centerCrop()
            .placeholder(android.R.color.darker_gray)
            .into(gameImage)
    }
}