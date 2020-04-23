package com.victoryzzi.android.apps.opgg.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackBar(msg: String) {
    Snackbar.make(
        this,
        msg,
        Snackbar.LENGTH_SHORT
    ).show()
}