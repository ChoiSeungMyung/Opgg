package com.victoryzzi.android.apps.opgg.adapter.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * 범용적으로 쓰일 수 있는 BindingAdapter
 */


/**
 * Glide를 이용해 imageUrl로 이미지뷰 세팅
 **/
@BindingAdapter("imageUrl")
fun setImage(view: ImageView, imageUrl: String) {
    Glide.with(view)
        .load(imageUrl)
        .into(view)
}