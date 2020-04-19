package com.victoryzzi.android.apps.opgg.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.adapter.list.viewholder.LeagueListViewHolder
import com.victoryzzi.android.apps.opgg.loge
import com.victoryzzi.android.apps.opgg.model.League

class LeagueListAdapter : ListAdapter<League, LeagueListViewHolder>(LeagueDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueListViewHolder =
        LeagueListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_league,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LeagueListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LeagueDiffCallBack : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean = oldItem == newItem
}