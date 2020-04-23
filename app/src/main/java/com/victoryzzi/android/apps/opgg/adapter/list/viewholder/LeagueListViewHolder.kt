package com.victoryzzi.android.apps.opgg.adapter.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.victoryzzi.android.apps.opgg.databinding.ListItemLeagueBinding
import com.victoryzzi.android.apps.opgg.data.model.League

class LeagueListViewHolder (private val binding: ListItemLeagueBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: League) {
        binding.league = item
    }
}