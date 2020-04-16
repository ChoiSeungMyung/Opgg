package com.victoryzzi.android.apps.opgg.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.adapter.GameListAdapter
import com.victoryzzi.android.apps.opgg.loge
import com.victoryzzi.android.apps.opgg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@UseExperimental(ExperimentalCoroutinesApi::class)
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    private val SUMMONER_URL = "https://codingtest.op.gg/api/summoner/genetory"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val gameListAdapter = GameListAdapter()
        game_list.adapter = gameListAdapter


        mainViewModel.requestGame("${System.currentTimeMillis()}")
        mainViewModel.responseGameLiveData.observe(this, Observer {
            gameListAdapter.submitList(it.games)
        })
    }
}
