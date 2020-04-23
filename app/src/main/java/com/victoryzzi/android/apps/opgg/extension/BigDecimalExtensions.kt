package com.victoryzzi.android.apps.opgg.extension

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 소수점 2째자리까지 표현하기 위한 확장 함수
 */
fun BigDecimal.rounded(multiple: Int, roundingMode: RoundingMode = RoundingMode.HALF_EVEN) =
    this.setScale(multiple, roundingMode) ?: 0f.toBigDecimal()