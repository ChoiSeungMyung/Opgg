package com.victoryzzi.android.apps.opgg.extension

import org.junit.Assert.*
import org.junit.Test

class BigDecimalExtensionsKtTest {

    /**
     * 소수점 2자리 까지 표시하기 위한 BigDecimal() Extension 함수 테스트
     */
    @Test
    fun bigDecimalRoundedTest() {
        val bigdecimal1 = 1.111116111111.toBigDecimal()
        val bigDecimal2 = 2.55555555555.toBigDecimal()
        val bigDecimal3 = 3.55666666666.toBigDecimal()
        val bigDecimal4 = 4.5545.toBigDecimal()

        assertTrue(bigdecimal1.rounded(2) == 1.11.toBigDecimal())
        assertTrue(bigDecimal2.rounded(2) == 2.56.toBigDecimal())
        assertTrue(bigDecimal3.rounded(2) == 3.56.toBigDecimal())
        assertTrue(bigDecimal4.rounded(2) == 4.55.toBigDecimal())
    }
}