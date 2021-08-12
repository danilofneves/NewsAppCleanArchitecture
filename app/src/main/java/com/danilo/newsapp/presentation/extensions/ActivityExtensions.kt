package com.danilo.newsapp.presentation.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String) {
    Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
}