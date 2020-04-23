package com.victoryzzi.android.apps.opgg.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.victoryzzi.android.apps.opgg.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    /**
     * Summoner name이 맞게 표현 되고 있는지 확인
     */
    @ExperimentalCoroutinesApi
    @Test
    fun summonerName() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.summoner_name))
            .check(matches(withText("genetory")))
    }
}