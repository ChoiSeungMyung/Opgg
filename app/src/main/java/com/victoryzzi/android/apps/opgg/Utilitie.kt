package com.victoryzzi.android.apps.opgg

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan

/**
 * "K / D / A" 형태에서 D의 텍스트색상이 자주 바뀌기 때문에
 * 유틸리티로 따로 관리
 */
fun getKDASpannable(kdaString: String): SpannableStringBuilder {
    val startIndex = kdaString.indexOfFirst { it == "/".single() } + 1
    val lastIndex = kdaString.indexOfLast { it == "/".single() }

    return SpannableStringBuilder(kdaString).apply {
        setSpan(
            ForegroundColorSpan(Color.rgb(82, 89, 95)),
            0,
            startIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(Color.rgb(232, 64, 87)),
            startIndex,
            lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(Color.rgb(82, 89, 95)),
            lastIndex,
            kdaString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}