package com.victoryzzi.android.apps.opgg.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.databinding.ActivityMainBinding
import com.victoryzzi.android.apps.opgg.loge
import com.victoryzzi.android.apps.opgg.model.League
import com.victoryzzi.android.apps.opgg.model.Summoner
import com.victoryzzi.android.apps.opgg.model.TierRank
import com.victoryzzi.android.apps.opgg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var actMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java).run {
            requestGame("${System.currentTimeMillis()}")
            requestSummoner("genetory")

            responseGameLiveData.observe(this@MainActivity, Observer {
                gameListAdapter.submitList(it.games)
            })

            responseSummonerLiveData.observe(this@MainActivity, Observer {
                actMainBinding.summoner = it.summoner
                leagueListAdapter.submitList(it.summoner.leagues)
            })

            this
        }

        summoner_league_list.adapter = mainViewModel.leagueListAdapter
        game_list.adapter = mainViewModel.gameListAdapter
    }
}
