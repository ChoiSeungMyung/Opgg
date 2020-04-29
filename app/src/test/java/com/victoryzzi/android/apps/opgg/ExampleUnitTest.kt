package com.victoryzzi.android.apps.opgg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.victoryzzi.android.apps.opgg.extension.asCallbackFlow
import com.victoryzzi.android.apps.opgg.service.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @ExperimentalCoroutinesApi
    @Test
    fun test() {
        val requestGameLiveData = MutableLiveData<String>()

        val responseGameLiveData = requestGameLiveData.switchMap {
            runBlocking {
                withContext(Dispatchers.Main) {
                    liveData {
                        NetworkHelper.gameService.loadGames("${System.currentTimeMillis()}")
                            .asCallbackFlow()
                            .catch { e ->
                                e.printStackTrace()
                            }.collect {
                                assert(it.games.size == 20)
                                emit(it)
                            }
                    }
                }
            }
        }
    }
}
