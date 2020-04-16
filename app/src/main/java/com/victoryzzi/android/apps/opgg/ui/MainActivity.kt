package com.victoryzzi.android.apps.opgg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.adapter.GameListAdapter
import com.victoryzzi.android.apps.opgg.loge
import com.victoryzzi.android.apps.opgg.model.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val SUMMONER_URL = "https://codingtest.op.gg/api/summoner/genetory"
    private val GAME_URL =
        "https://codingtest.op.gg/api/summoner/genetory/matches?lastMatch={createDate}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf(
            Game(
                "257807720",
                true,
                "3422",
                "https://opgg-static.akamaized.net/images/lol/champion/Qiyana.png",
                "ACE",
                listOf(
                    "https://opgg-static.akamaized.net/images/lol/spell/SummonerTeleport.png",
                    "https://opgg-static.akamaized.net/images/lol/spell/SummonerFlash.png",
                    "https://opgg-static.akamaized.net/images/lol/perk/8229.png",
                    "https://opgg-static.akamaized.net/images/lol/perkStyle/8300.png"
                ),
                "10",
                "7",
                "1",
                "23%",
                "Ranked Solo",
                "1571653912",
                listOf("https://opgg-static.akamaized.net/images/lol/item/3198.png", "", "", "", "", ""),
                ""
            )
        )

        val gameListAdapter = GameListAdapter()
        game_list.adapter = gameListAdapter

        gameListAdapter.submitList(list)

        loge("${gameListAdapter.currentList.size}")
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val summoner = URL(SUMMONER_URL).readText()
//
//            Log.e("TAG", summoner)
//            println(summoner)
//        }
    }
}
