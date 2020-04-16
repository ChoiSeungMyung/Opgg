package com.victoryzzi.android.apps.opgg.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.victoryzzi.android.apps.opgg.databinding.ListItemGameBinding
import com.victoryzzi.android.apps.opgg.loge
import com.victoryzzi.android.apps.opgg.model.Game

class GameListViewHolder(private val binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Game) {
        binding.apply {
            game = item
        }
    }
}