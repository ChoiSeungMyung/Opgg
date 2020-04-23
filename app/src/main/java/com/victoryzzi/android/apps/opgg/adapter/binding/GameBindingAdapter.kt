package com.victoryzzi.android.apps.opgg.adapter.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.getKDASpannable
import com.victoryzzi.android.apps.opgg.model.games.Item
import com.victoryzzi.android.apps.opgg.model.games.Spell
import com.victoryzzi.android.apps.opgg.ui.view.GameInfo
import com.victoryzzi.android.apps.opgg.ui.view.GameItems
import com.victoryzzi.android.apps.opgg.ui.view.GameSpells
import kotlinx.android.synthetic.main.layout_game_info.view.*
import kotlinx.android.synthetic.main.layout_game_items.view.*
import kotlinx.android.synthetic.main.layout_game_spells.view.*
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bg")
fun setGameInfoBackgroundColor(view: GameInfo, resource: Int) {
    view.game_info_wrapper.setBackgroundColor(resource)
}

/**
 * gameLength를 00:00으로 표현
 * 10분 || 10초 를 안넘는 경우를 나누어 표현
 */
@BindingAdapter("gameLength")
fun setGameLength(view: GameInfo, gameLength: String) {
    val minute = gameLength.toLong() / 60
    val seconds = gameLength.toLong() % 60

    view.game_length.text = when (minute < 10) {
        true -> {
            when (seconds < 10) {
                true -> "0$minute:0$seconds"
                false -> "0$minute:$seconds"
            }
        }
        false -> {
            when (seconds < 10) {
                true -> "$minute:0$seconds"
                false -> "$minute:$seconds"
            }
        }
    }
}

@BindingAdapter("isWin")
fun setIsWinText(view: GameInfo, isWin: String) {
    view.game_is_win.text = isWin
}

@BindingAdapter("KDA")
fun setKDAText(view: TextView, kda: String) {
    view.text = getKDASpannable(kda)
}

/**
 * index를 이용해 들어가야할 스펠 순서를 맞춤
 */
@BindingAdapter("spells")
fun setSpells(view: GameSpells, spells: List<Spell>) {
    val viewList = listOf(view.spells_first, view.spells_second)
    spells.forEachIndexed { i, spell ->
        when (spell.imageUrl.isBlank()) {
            false -> {
                Glide.with(view)
                    .load(spell.imageUrl)
                    .into(viewList[i])
            }
        }
    }
}

/**
 * index를 이용해 들어가야할 퍽(?) 순서를 맞춤
 */
@BindingAdapter("perks")
fun setPerks(view: GameSpells, perks: List<String>) {
    val viewList = listOf(view.perk_first, view.perk_second)
    perks.forEachIndexed { i, url ->
        when (url.isEmpty()) {
            false -> {
                Glide.with(view).load(url).into(viewList[i])
            }
        }
    }
}

/**
 * 게임 생성 시간에 따라
 * ~초전
 * ~분전
 * ~시간전
 * 으로 표현 하고
 * 24시간이 넘는 다면 날짜로 표현
 */
@BindingAdapter("createDate")
fun setCreateDate(view: TextView, createDate: String) {
    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val parsedDate = createDate.toLong()
    val currentTime = System.currentTimeMillis()

    when (val time = (currentTime - parsedDate).toInt()) {
        in 0..60 -> {
            view.text =
                view.resources.getQuantityString(R.plurals.game_create_date_seconds_ago, time, time)
        }
        in 61..3600 -> {
            view.text = view.resources.getQuantityString(
                R.plurals.game_create_date_minutes_ago,
                (time % 60),
                (time % 60)
            )
        }
        in 3601..86400 -> {
            view.text = view.resources.getQuantityString(
                R.plurals.game_create_date_hours_ago,
                (time / 3600),
                (time / 3600)
            )
        }
        else -> {
            view.text = simpleDateFormat.format(Date(parsedDate))
        }
    }
}

@BindingAdapter("items")
fun setItems(view: GameItems, items: List<Item>) {
    val viewList = listOf(
        view.game_item_1,
        view.game_item_2,
        view.game_item_3,
        view.game_item_4,
        view.game_item_5,
        view.game_item_6
    )

    items.forEachIndexed { i, item ->
        when (item.imageUrl.isBlank()) {
            false -> {
                Glide.with(view)
                    .load(item.imageUrl)
                    .into(viewList[i])
            }
        }
    }
}

/**
 * largestMultiKill에 따라 뱃지 표현
 * 공백문자일때도 뱃지는 Empty와 같은 취급이기 때문에
 * blank()로 조건 걸어줌
 */
@BindingAdapter("largestMultiKill")
fun setLargestMultiKill(view: TextView, msg: String) {
    when (msg.isBlank()) {
        true -> view.visibility = View.GONE
        false -> view.text = msg
    }
}