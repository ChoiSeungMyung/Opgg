package com.victoryzzi.android.apps.opgg.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.adapter.list.viewholder.GameListViewHolder
import com.victoryzzi.android.apps.opgg.model.Game

class GameListAdapter : ListAdapter<Game, GameListViewHolder>(GamesDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder =
        GameListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_game,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GamesDiffCallBack : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
        oldItem.gameId == newItem.gameId

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
        oldItem.gameId == newItem.gameId
}