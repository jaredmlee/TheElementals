package com.jaredlee.theelementals.gameObjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location

class Key(val game: GameObject) : GameObject() {
    override fun render(canvas: Canvas, paint: Paint) {
        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)
        paint.color = Color.YELLOW
        canvas.drawRect(60f, 20f, 80f, 90f, paint)
        canvas.drawCircle(70f, 90f, 10f, paint)
        canvas.drawRect(40f, 30f, 60f, 40f, paint)
        canvas.drawRect(40f, 45f, 60f, 55f, paint)
        canvas.drawRect(40f, 30f, 45f, 55f, paint)
        paint.color = Color.GRAY
        canvas.drawCircle(70f, 90f, 5f, paint)
    }
    }