package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Door(val game: GameObject) : GameObject() {
    override fun render(canvas: Canvas, paint: Paint) {

        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        //todo intense improvements needed for door
        canvas.translate(myX, myY)
        paint.color = Color.rgb(165,42,42)
        canvas.drawRect(30f, 10f, 92f, 92f, paint)
        paint.color = Color.BLACK
        paint.strokeWidth = 3f
        canvas.drawLine(55f,10f,55f,90f,paint)
        canvas.drawLine(70f,10f,70f,90f,paint)
        canvas.drawLine(85f,10f,85f,90f,paint)
        paint.color = Color.rgb(255,248,220)
        canvas.drawCircle(40f,60f,7f,paint)
    }
}