package com.jaredlee.theelementals.gameObjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location

class Grass(val game: GameObject) : GameObject() {
    override fun render(canvas: Canvas, paint : Paint){
        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize
        //todo actually make it look good
        canvas.translate(myX, myY)
        paint.color = Color.rgb(154,205,50)
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)
    }
}