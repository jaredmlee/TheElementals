package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint

abstract class GameObject {
    var state = State()

    open fun update(elapsedTime: Long) {}
    open fun render(canvas: Canvas, paint: Paint) {}
    var inputs = mutableListOf<Location>()
    fun addInput( x: Float, y: Float) {
        val newInput = Location(x, y)
        inputs.add(newInput)
    }
    fun clearInputs(){
        inputs.clear()
    }
}