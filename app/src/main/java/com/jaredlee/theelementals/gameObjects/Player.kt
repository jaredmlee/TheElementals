package com.jaredlee.theelementals.gameObjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location
import com.jaredlee.theelementals.ResetStates

class Player(val game: GameObject) : GameObject() {
    override fun update(elapsedTime: Long) {
        val turn: String = game.state["turn"]
        val playerHealth: Int  = game.state["playerHealth"]
        val swordsmanPos: Location = game.state["swordsmanPos"]
        val swordsmanHealth: Int = game.state["swordsmanHealth"]
        val cellSize: Int  = game.state["cellSize"]
        var interacting = false;
        if (playerHealth < 1){
            state["alive"] = false;
        }
        if(game.inputs.isNotEmpty() && turn == "player" && state["alive"]) {
            val location = game.inputs[0]
            val xLoc = (location.x/cellSize).toInt()
            val yLoc = (location.y/cellSize).toInt()
            val curCoords : Location = state["coords"]
            val doorLoc: Location = game.state["doorPos"]
            val doorLoc2: Location = game.state["doorPos2"]
            val lockedDoorPos: Location = game.state["LockedDoorPos"]
            val wallLocs: ArrayList<Location> = game.state["wallLocs"]
            val storeLoc: Location = game.state["storePos"]
            val coins: Int  = game.state["coins"]
            for (i in wallLocs){
                if (xLoc.toFloat() == i.x && yLoc.toFloat() == i.y){
                    interacting = true
                }
            }
            if(checkIfNeighbor(curCoords,doorLoc.x.toInt(),doorLoc.y.toInt())) { //walk through door
                if (xLoc.toFloat() == doorLoc.x && yLoc.toFloat() == doorLoc.y) {
                    interacting = true
                    val doorLevel: Int = state["doorLevel"]
                    val resetStates = ResetStates(game)
                    resetStates.reset(doorLevel)
                    game.state["level"] = state["doorLevel"]
                }
            }
            if(checkIfNeighbor(curCoords,doorLoc2.x.toInt(),doorLoc2.y.toInt())) { //walk through door
                if (xLoc.toFloat() == doorLoc2.x && yLoc.toFloat() == doorLoc2.y) {
                    interacting = true
                    val doorLevel: Int = state["doorLevel2"]
                    val resetStates = ResetStates(game)
                    resetStates.reset(doorLevel)
                    game.state["level"] = state["doorLevel2"]
                }
            }
            if(checkIfNeighbor(curCoords,lockedDoorPos.x.toInt(),lockedDoorPos.y.toInt())) {//walk though locked door
                if (xLoc.toFloat() == lockedDoorPos.x && yLoc.toFloat() == lockedDoorPos.y) {
                    interacting = true
                    val doorLevel: Int = state["lockedDoorLevel"]
                    val resetStates = ResetStates(game)
                    resetStates.reset(doorLevel)
                    game.state["level"] = state["lockedDoorLevel"]
                }
            }
            if(checkIfNeighbor(curCoords,storeLoc.x.toInt(),storeLoc.y.toInt())) {//walk into store
                if (xLoc.toFloat() == storeLoc.x && yLoc.toFloat() == storeLoc.y) {
                    interacting = true
                    game.state["level"] = 1000
                }
            }
            if (checkIfNeighbor(curCoords,xLoc,yLoc)){
                if (xLoc.toFloat() == swordsmanPos.x && yLoc.toFloat() == swordsmanPos.y){ // attack swordsman
                    interacting = true
                    game.state["swordsmanHealth"] = swordsmanHealth-1
                    if(swordsmanHealth==1){
                        game.state["coins"] = coins+1
                    }
                }
                if (!interacting) {
                    state["coords"] = Location(xLoc.toFloat(), yLoc.toFloat()) //move in direction of touch
                    game.clearInputs()
                }
            }
            game.clearInputs()
            game.state["playerPos"] = state["coords"]
            game.state["interacting"] = interacting
            game.state["turn"] = "swordsman"

        }
    }
    fun checkIfNeighbor(myLoc: Location, otherX: Int, otherY: Int): Boolean {
        if (myLoc.x == otherX.toFloat()) {
            if (myLoc.y == otherY + 1f || myLoc.y == otherY - 1f) {
                return true
            }
        }
        if (myLoc.y == otherY.toFloat()) {
            if (myLoc.x == otherX + 1f || myLoc.x == otherX - 1f) {
                return true
            }
        }
        return false;
    }

    override fun render(canvas: Canvas, paint: Paint) {
        val playerHealth: Int  = game.state["playerHealth"]
        val coords: Location = state["coords"]
        val cellSize: Int = game.state["cellSize"]
        val myX = coords.x * cellSize
        val myY = coords.y * cellSize

        //todo add a sash thingy and some other shiz
        canvas.translate(myX, myY)
        paint.color = Color.rgb(0,0,45)//dark blue
        canvas.drawRect(55f, 50f, 67f, 102f, paint)//left leg
        canvas.drawRect(25f, 50f,37f,102f,paint)//right leg
        paint.color = Color.rgb(139,69,19)
        canvas.drawRect(25f,95f,42f,102f,paint)// left shoe
        canvas.drawRect(25f,90f,37f,102f,paint)
        canvas.drawRect(55f,95f,72f,102f,paint)//right shoe
        canvas.drawRect(55f,90f,67f,100f,paint)
        paint.color = Color.rgb(0,0,45)
        canvas.drawRect(25f,35f,67f,77f,paint)// torso
        paint.color = Color.rgb(139,69,19)//brown
        canvas.drawRect(25f,65f,67f,70f,paint)//belt
        if (state["alive"]) {
            paint.color = Color.rgb(232,190,172)
            canvas.drawRect(30f,30f,60f,35f,paint)//neck
            canvas.drawRect(27f,5f,63f,30f,paint)//head
            paint.color = Color.rgb(139,69,0)
            canvas.drawRect(27f,3f,63f,11f,paint)//hair
            canvas.drawRect(55f,5f,63f,15f,paint)
            canvas.drawRect(58f,9f,70f,15f,paint)
            canvas.drawRect(63f,9f,70f,18f,paint)
            canvas.drawRect(27f,5f,37f,15f,paint)
            canvas.drawRect(23f,15f,34f,25f,paint)
            canvas.drawRect(23f,25f,27f,27f,paint)
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
        paint.color = Color.rgb(139,69,19)
        canvas.drawRect(15f,35f,33f,45f,paint)//left shoulder
        canvas.drawRect(10f,40f,30f,45f,paint)
        canvas.drawRect(60f,35f,73f,45f,paint)//right shoulder
        canvas.drawRect(60f,40f,80f,45f,paint)
        paint.color = Color.rgb(0,0,45)//dark blue
        canvas.drawRect(15f,45f,22f,63f,paint)//left arm
        canvas.drawRect(15f,56f,30f,63f,paint)
        paint.color = Color.rgb(232,190,172)
        canvas.drawRect(30f,56f,37f,63f,paint)//hand
        paint.color = Color.rgb(0,0,45)//dark blue
        canvas.drawRect(68f,45f,75f,63f,paint)//right arm
        canvas.drawRect(37f,56f,75f,63f,paint)
        paint.color = Color.rgb(218,165,32)
        canvas.drawRect(30f,63f,37f,72f,paint)//sword handle
        canvas.drawRect(27f,51f,40f,56f,paint)
        paint.color = Color.LTGRAY
        canvas.drawRect(30f,2f,37f,51f,paint)//sword
        //health bar
        paint.color = Color.GREEN
        if (playerHealth==5) {
            canvas.drawRect(20f, -10f, 80f, 0f, paint)
        }
        else if (playerHealth==4) {
            canvas.drawRect(20f, -10f, 70f, 0f, paint)
        }
        else if (playerHealth==3) {
            paint.color = Color.YELLOW
            canvas.drawRect(20f, -10f, 60f, 0f, paint)
        }
        else if (playerHealth == 2){
            paint.color = Color.YELLOW
            canvas.drawRect(20f, -10f, 50f, 0f, paint)
        }
        else if (playerHealth==1){
            paint.color = Color.RED
            canvas.drawRect(20f, -10f, 40f, 0f, paint)
        }
    }
}