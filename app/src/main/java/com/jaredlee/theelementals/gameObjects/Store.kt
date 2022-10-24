package com.jaredlee.theelementals.gameObjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location

class Store(val game: GameObject) : GameObject() {
    override fun render(canvas: Canvas, paint: Paint) {

        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)
        paint.color = Color.RED
        canvas.drawCircle(50f,50f,50f,paint)
    }
}