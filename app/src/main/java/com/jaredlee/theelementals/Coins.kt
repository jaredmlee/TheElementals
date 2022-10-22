package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Coins(val game: GameObject) : GameObject() {
    override fun render(canvas: Canvas, paint: Paint){
        val coords = Location(0f,0f)
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        //todo make coin look more like a coin
        canvas.translate(myX, myY)
        paint.color = Color.YELLOW
        canvas.drawCircle(30f,30f,20f,paint)//coin icon
        val coins: Int = game.state["coins"]
        val cnStr = coins.toString()
        paint.color = Color.BLACK
        paint.textSize = 40f
        canvas.drawText(cnStr,60f,43f,paint)//number of coins
    }

}