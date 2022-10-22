package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint

class StoreScreen(val game: GameObject): GameObject() {
    //TODO ADD GOOD RENDERS AND ADD ABILITY TO BUY ITEMS. MAKE AN INVENTORY THAT YOU CAN
    //TODO ACCESS FROM ANYWHERE AND EQUIP CERTAIN ITEMS
    override fun update(deltaTime: Long){
        val cellSize: Int = game.state["cellSize"]
        if(game.inputs.isNotEmpty()){
            val location = game.inputs[0]
            val xLoc = (location.x/cellSize).toInt()
            val yLoc = (location.y/cellSize).toInt()
            if (xLoc.toFloat()==0f && yLoc.toFloat()==0f){
                game.state["level"] = 5
            }
            game.clearInputs()
        }

    }
    override fun render(canvas: Canvas, paint: Paint){
        val coords= Location(5f,5f)
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize
        canvas.save()
        canvas.translate(myX, myY)
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)
        canvas.restore()
        canvas.translate(0f,0f)
        canvas.drawRect(0f, 0f, cellSize.toFloat(), cellSize.toFloat(), paint)
    }
}