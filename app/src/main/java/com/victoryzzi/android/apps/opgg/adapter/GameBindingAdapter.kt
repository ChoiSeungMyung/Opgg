package com.victoryzzi.android.apps.opgg.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victoryzzi.android.apps.opgg.R
import com.victoryzzi.android.apps.opgg.model.Item
import com.victoryzzi.android.apps.opgg.model.Spell
import com.victoryzzi.android.apps.opgg.ui.view.GameInfo
import com.victoryzzi.android.apps.opgg.ui.view.GameItems
import com.victoryzzi.android.apps.opgg.ui.view.GameSpells
import com.victoryzzi.android.apps.opgg.ui.view.Profile
import kotlinx.android.synthetic.main.layout_game_info.view.*
import kotlinx.android.synthetic.main.layout_game_items.view.*
import kotlinx.android.synthetic.main.layout_game_spells.view.*
import kotlinx.android.synthetic.main.layout_profile.view.*
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bg")
fun setGameInfoBackgroundColor(view: GameInfo, resource: Int) {
    view.game_info_wrapper.setBackgroundColor(resource)
}

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

@BindingAdapter("profileImage")
fun setChampionImage(view: Profile, championImageUrl: String) {
    Glide.with(view).load(championImageUrl).apply {
        circleCrop()
    }.into(view.profile_image)
}

@BindingAdapter("profileText")
fun setBadge(view: Profile, msg: String) {
    println(msg)
    when (msg) {
        "ACE" -> {
            view.profile_text.apply {
                setBackgroundResource(R.drawable.ic_badge_ace)
                setTypeface(typeface, Typeface.BOLD)
                setTextSize(TypedValue.COMPLEX_UNIT_PX, view.resources.getDimension(R.dimen.sp_10))
                text = msg
            }
        }

        "MVP" -> {
            view.profile_text.apply {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, view.resources.getDimension(R.dimen.sp_10))
                setBackgroundResource(R.drawable.ic_badge_mvp)
                setTypeface(typeface, Typeface.BOLD)
                text = msg
            }
        }
        "" -> {
            view.profile_text.visibility = View.GONE
        }
        else -> {
            view.profile_text.apply {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, view.resources.getDimension(R.dimen.sp_12))
                setBackgroundResource(R.drawable.ic_badge_level)
                text = msg
            }
        }
    }
}

@BindingAdapter("KDA")
fun setKDAText(view: TextView, kda: String) {
    val startIndex = kda.indexOfFirst { it == "/".single() } + 1
    val lastIndex = kda.indexOfLast { it == "/".single() }
    val spannable = SpannableStringBuilder(kda).apply {
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
            kda.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    view.text = spannable
}

@BindingAdapter("spells")
fun setSpells(view: GameSpells, spells: List<Spell>) {
    val viewList = listOf(view.spells_first, view.spells_second)
    spells.forEachIndexed { i, spell ->
        when (spell.imageUrl.isBlank()) {
            false -> {
                Glide.with(view).load(spell.imageUrl).into(viewList[i])
            }
        }
    }
}

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
                Glide.with(view).load(item.imageUrl).into(viewList[i])
            }
        }
    }
}

@BindingAdapter("largestMultiKill")
fun setLargestMultiKill(view: TextView, msg: String) {
    when (msg) {
        "" -> view.visibility = View.GONE
        else -> view.text = msg
    }
}