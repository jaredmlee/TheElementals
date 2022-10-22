package com.jaredlee.theelementals

class State {
    private val state = mutableMapOf<String, Any>()
    operator fun <T> get(key: String): T {
        return state[key] as T
    }

    operator fun set(key: String, value: Any) {
        state[key] = value
    }
}