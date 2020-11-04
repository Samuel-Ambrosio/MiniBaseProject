package com.ghrisav.minibaseproject.common.extensions

val Any.TAG: String
    get() {
        return javaClass.simpleName
    }