package com.victoryzzi.android.apps.opgg.adapter.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.victoryzzi.android.apps.opgg.databinding.ListItemGameBinding
import com.victoryzzi.android.apps.opgg.data.model.games.Game

class GameListViewHolder(private val binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Game) {
        binding.game = item
    }
}