package com.ghrisav.minibaseproject.common.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.ghrisav.minibaseproject.common.utils.SingleClickListener

/* Public ext functions */
fun View.doVisible() {
    visibility = View.VISIBLE
}

fun View.doGone() {
    visibility = View.GONE
}

fun View.doInvisible() {
    visibility = View.INVISIBLE
}

fun View.doVisibleOrGone(condition: () -> Boolean) {
    doVisibleOr(View.GONE, condition)
}

fun View.doVisibleOrInvisible(condition: () -> Boolean) {
    doVisibleOr(View.INVISIBLE, condition)
}

fun View?.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    val layoutParams = this?.layoutParams
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams.setMargins(left, top, right, bottom)
        this?.layoutParams = layoutParams
    }
}

fun View.hideKeyboard() {
    val inputManager = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val inputManager = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.setOnSingleClickListener(action: () -> Unit) {
    setOnClickListener(SingleClickListener {
        action()
    })
}

/* Private ext functions */
private fun View.doVisibleOr(orVisibility: Int, condition: () -> Boolean) {
    visibility = if (condition()) View.VISIBLE else orVisibility
}