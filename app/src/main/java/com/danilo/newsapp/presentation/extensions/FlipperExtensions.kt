package com.danilo.newsapp.presentation.extensions

import android.widget.ViewFlipper

object FlipperStatus {
    const val LOADING = 0
    const val DATA = 1
    const val ERROR = 2
    const val EMPTY = 3
}

fun ViewFlipper.toLoading() {
    this.displayedChild = FlipperStatus.LOADING
}

fun ViewFlipper.toData() {
    this.displayedChild = FlipperStatus.DATA
}

fun ViewFlipper.toError() {
    this.displayedChild = FlipperStatus.ERROR
}

fun ViewFlipper.toEmpty() {
    this.displayedChild = FlipperStatus.EMPTY
}

