package com.jaredlee.theelementals.gameObjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location

class CastleFloor(val game: GameObject) : GameObject() {
     override fun render(canvas: Canvas, paint : Paint){
        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        //todo make it look better
        canvas.translate(myX, myY)
        paint.color = Color.GRAY
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        canvas.drawLine(0f,0f,cellSize.toFloat(),0f,paint)
        canvas.drawLine(0f,0f,0f,cellSize.toFloat(),paint)
        canvas.drawLine(0f,cellSize.toFloat(),0f,0f,paint)
        canvas.drawLine(cellSize.toFloat(),0f,0f,0f,paint)
    }
}