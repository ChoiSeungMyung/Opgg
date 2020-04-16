package com.victoryzzi.android.apps.opgg.extension

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
fun <T> Call<T>.asCallbackFlow() = callbackFlow<T> {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            when(response.isSuccessful) {
                true -> response.body()?.let {
                    offer(it)
                } ?: close()

                else -> close()
            }
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            close(throwable)
        }
    })

    awaitClose()
}