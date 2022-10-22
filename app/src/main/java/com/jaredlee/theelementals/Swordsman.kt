package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Swordsman(val game: GameObject) : GameObject() {
    override fun update(elapsedTime: Long) {
        val turn : String = game.state["turn"]
        val swordsmanHealth: Int  = game.state["swordsmanHealth"]
        val wallLocs: ArrayList<Location> = game.state["wallLocs"]
        val cellSize: Int  = game.state["cellSize"]
        if (swordsmanHealth < 1){
            state["alive"] = false;
        }
        if (swordsmanHealth > 0){
            state["alive"] = true;
        }
        val playerHealth: Int = game.state["playerHealth"]
        if ( turn == "swordsman" && state["alive"]) { //move randomly
            val currCoords: Location = state["coords"]
            val playerPos: Location = game.state["playerPos"]
            val playerX = playerPos.x
            val playerY = playerPos.y
            val currX = (currCoords.x)
            val currY = (currCoords.y)
            val random = (0..3).random()
            if (random == 0)
                if (currX<6)
                state["coords"] = Location(currX+1f,currY)
                else
                state["coords"] = Location(currX - 1f, currY)
            if (random == 1) {
                if (currY<12){
                state["coords"] = Location(currX, currY + 1f)}
                else
                state["coords"] = Location(currX,currY-1f)
            }
            if (random == 2)
                if (currY> 0)
                state["coords"] = Location(currX,currY-1f)
                else
                state["coords"] = Location(currX, currY + 1f)
            if (random ==3) {
                if (currX>0)
                state["coords"] = Location(currX - 1f, currY)
                else
                state["coords"] = Location(currX+1f,currY)
            }
            val currCoords2: Location = state["coords"]
            val currX2 = (currCoords2.x)
            val currY2 = (currCoords2.y)
            if (currX2+1f == playerX || currX2 == playerX){ // attack the player
               if ( currY2+1f == playerY || currY2 == playerY){
                   game.state["playerHealth"] = playerHealth-1;
                }
            }
            for (i in wallLocs){ //don't run into walls
                if (currX2+1f == i.x || currX2 == i.x) {
                    if (currY2 + 1f == i.y || currY2 == i.y) {
                        state["coords"] = currCoords
                    }
                }
            }
            game.state["swordsmanPos"] = state["coords"]

        }
        game.state["turn"] = "player"


    }

    override fun render(canvas: Canvas, paint: Paint) {
        val swordsmanHealth: Int  = game.state["swordsmanHealth"]
        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        canvas.translate(myX, myY)
        paint.color = Color.rgb(136,8,8)
        canvas.drawRect(55f, 50f, 67f, 102f, paint)//left leg
        canvas.drawRect(25f, 50f,37f,102f,paint)//right leg
        paint.color = Color.BLACK
        canvas.drawRect(25f,95f,42f,102f,paint)// left shoe
        canvas.drawRect(25f,90f,37f,102f,paint)
        canvas.drawRect(55f,95f,72f,102f,paint)//right shoe
        canvas.drawRect(55f,90f,67f,100f,paint)
        paint.color = Color.rgb(136,8,8)
        canvas.drawRect(25f,35f,67f,77f,paint)// torso
        paint.color = Color.BLACK
        canvas.drawRect(25f,65f,67f,70f,paint)//belt
        if (state["alive"]) {
            paint.color = Color.rgb(232,190,172)
            canvas.drawRect(30f,30f,60f,35f,paint)//neck
            canvas.drawRect(27f,5f,63f,30f,paint)//head
            paint.color = Color.rgb(251,231,161)
            canvas.drawRect(27f,3f,63f,11f,paint)//hair
            canvas.drawRect(55f,5f,63f,15f,paint)
            canvas.drawRect(58f,9f,70f,15f,paint)
            canvas.drawRect(63f,9f,70f,18f,paint)
            canvas.drawRect(27f,5f,37f,15f,paint)
            canvas.drawRect(23f,15f,34f,25f,paint)
            canvas.drawRect(23f,25f,27f,27f,paint)
            paint.color = Color.rgb(136,8,8)
            canvas.drawRect(24f,7f,73f,12f,paint)//cap
            canvas.drawRect(27f,2f,69f,12f,paint)
            paint.color = Color.BLACK
            canvas.drawRect(57f,17f,63f,23f,paint)//right eye
            canvas.drawRect(43f,17f,49f,23f,paint) // left eye
            paint.color = Color.WHITE
            canvas.drawRect(49f,25f,57f,28f,paint)
        }
        else{
            paint.color = Color.RED
            paint.strokeWidth= 3f
            canvas.drawLine(45f,35f,20f,15f,paint)
            canvas.drawLine(45f,35f,70f,15f,paint)
            canvas.drawLine(20f,15f,0f,40f,paint)
            canvas.drawLine(70f,15f,100f,40f,paint)
        }
        paint.color = Color.BLACK
        canvas.drawRect(15f,35f,33f,45f,paint)//left shoulder
        canvas.drawRect(10f,40f,30f,45f,paint)
        canvas.drawRect(60f,35f,73f,45f,paint)//right shoulder
        canvas.drawRect(60f,40f,80f,45f,paint)
        paint.color = Color.rgb(136,8,8)
        canvas.drawRect(15f,45f,22f,63f,paint)//left arm
        canvas.drawRect(15f,56f,30f,63f,paint)
        paint.color = Color.rgb(136,8,8)
        canvas.drawRect(68f,45f,75f,63f,paint)//right arm
        canvas.drawRect(37f,56f,75f,63f,paint)
        paint.color = Color.rgb(232,190,172)
        canvas.drawRect(50f,56f,57f,63f,paint)//hand
        paint.color = Color.rgb(218,165,32)
        canvas.drawRect(50f,63f,57f,72f,paint)//sword handle
        canvas.drawRect(47f,51f,60f,56f,paint)
        paint.color = Color.LTGRAY
        canvas.drawRect(50f,2f,57f,51f,paint)//sword

        if (swordsmanHealth==2) {
            paint.color = Color.GREEN
            canvas.drawRect(20f, -10f, 80f, 0f, paint)
        }
        else if (swordsmanHealth == 1){
            paint.color = Color.RED
            canvas.drawRect(20f, -10f, 40f, 0f, paint)
        }
    }
}