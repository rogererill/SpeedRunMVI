package com.erill.roger.feature.gameslist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erill.roger.feature.gameslist.R
import com.erill.roger.feature.gameslist.entities.Game

class GamesAdapter(private val clickListener: (Game) -> Unit) : RecyclerView.Adapter<GameViewHolder>() {

    var data: List<Game> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}