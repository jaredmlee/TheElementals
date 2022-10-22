package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Wall (val game: GameObject) : GameObject(){
    override fun render(canvas: Canvas, paint: Paint) {

        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)
        paint.color = Color.BLACK
        canvas.drawRect(0f,cellSize.toFloat(),cellSize.toFloat(),0f,paint)
    }
}