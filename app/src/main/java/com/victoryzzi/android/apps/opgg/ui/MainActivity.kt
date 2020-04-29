package com.victoryzzi.android.apps.opgg.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.databinding.ActivityMainBinding
import com.victoryzzi.android.apps.opgg.extension.makeSnackBar
import com.victoryzzi.android.apps.opgg.extension.networkCheck
import com.victoryzzi.android.apps.opgg.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var actMainBinding: ActivityMainBinding

    private val scrollChangeListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (!game_list.canScrollVertically(1) && networkCheck()) {
//                네트워크와 연결되어 있을때,
//                사용자가 스크롤을 리스트 마지막 아이템 까지 내리면
//                현재 목록 + 마지막 아이템 createDate를 파라미터로 데이터 요청
                mainViewModel.requestGameMore()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java).run {
//              Games 데이터와 관련된 LiveData observe : games초기화 & 전적 갱신
            responseGameLiveData.observe(this@MainActivity, Observer {
                actMainBinding.apply {
                    games = it
                    positions = it.positions[0]
                }
                gameListAdapter.submitList(it.games)
            })
//             Summoner 데이터와 관련된 LiveData observe : Summoner초기화 & 전적 갱신
            responseSummonerLiveData.observe(this@MainActivity, Observer {
                actMainBinding.summoner = it.summoner
                leagueListAdapter.submitList(it.summoner.leagues)
            })
//              LoadMore를 실행했을때 변경이 발생하는 LiveData : 게임목록 LoadMore
            responseGameLiveDataMore.observe(this@MainActivity, Observer { value ->
                value?.let {
                    gameListAdapter.submitList(it.games)
                }
            })

            this
        }

//        네트워크 연결 상태 확인 후 데이터 요청
        when (networkCheck()) {
            true -> {
                mainViewModel.apply {
                    requestGame("${System.currentTimeMillis()}")
                    requestSummoner("genetory")
                }
            }
            false -> main_root.makeSnackBar(getString(R.string.toast_check_connectivity))
        }
        summoner_league_list.adapter = mainViewModel.leagueListAdapter
        game_list.adapter = mainViewModel.gameListAdapter

        initView()
    }

    private fun initView() {
        btn_renew.setOnClickListener(this)
        game_list.addOnScrollListener(scrollChangeListener)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_renew -> {
                when (networkCheck()) {
                    true -> {
                        mainViewModel.apply {
                            requestSummoner("genetory")
                            requestGame("${System.currentTimeMillis()}")
                        }
                    }
                    false -> main_root.makeSnackBar(getString(R.string.toast_check_connectivity))
                }
            }
        }
    }
}
