package com.ghrisav.minibaseproject.common.utils

open class Event<out T>(private val content: T?) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun getHasBeenHandled() = hasBeenHandled

    fun peekContent(): T? = content
}