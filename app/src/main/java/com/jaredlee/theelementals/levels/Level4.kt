package com.jaredlee.theelementals.levels

import android.graphics.Canvas
import android.graphics.Paint
import com.jaredlee.theelementals.GameObject
import com.jaredlee.theelementals.Location
import com.jaredlee.theelementals.Render
import com.jaredlee.theelementals.gameObjects.*

class Level4 (private val game: GameObject): GameObject(){
    private var numCols = 7
    private var numRows = 13
    var player : GameObject
    var swordsman: GameObject
    var door: GameObject
    var door2: GameObject
    var coins: GameObject
    private var floorTiles: ArrayList<CastleFloor> = ArrayList()
    private var wallLocs: ArrayList<Location> = ArrayList()
    private var walls: ArrayList<Wall> = ArrayList()
    init{
        player = Player(game)
        player.state["coords"] = Location(6f,11f)
        player.state["alive"] = true
        swordsman = Swordsman(game)
        swordsman.state["coords"] = Location(4f,4f)
        swordsman.state["alive"] = true
        swordsman.state["elite"] = true
        door = Door(game)
        door.state["coords"] = Location(6f,6f)
        door2 = Door(game)
        door2.state["coords"] = Location(1f,11f)
        player.state["doorLevel"] = 3
        player.state["doorLevel2"] = 1
        coins = Coins(game)

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val floor = CastleFloor(game)
                floor.state["coords"] = Location(j.toFloat(), i.toFloat())
                floorTiles.add(floor)
            }
        }
        for (i in 0 until 12){
            val wall = Wall(game)
            wall.state["coords"] = Location(2f,i+1f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        game.state["wallLocs"] = wallLocs
    }
    fun resetGameStates(){
        game.state["wallLocs"] = wallLocs
        swordsman.state["coords"] = Location(4f,4f)
        game.state["swordsmanHealth"]  = 5
        swordsman.state["alive"] = true
        game.state["doorPos"] = Location(6f,6f)
        game.state["doorPos2"] = Location(1f,11f)
        //set lockedDoor to something ludicrous, would be nice to figure out a way to not have a locked door
        //on every level
        game.state["LockedDoorPos"] = Location(100f,100f)
        game.state["swordsmanPos"] = Location(4f,4f)
    }
    fun doFrame(deltaTime: Long){
        player.update(deltaTime)
        swordsman.update(deltaTime)
    }
    fun renderLevel2(canvas: Canvas, paint: Paint){
        val render = Render()
        render.renderFloor(canvas,paint, floorTiles)
        render.renderWalls(canvas,paint,walls)
        render.renderPlayer(canvas,paint,player)
        render.renderSwordsman(canvas,paint,swordsman)
        render.renderDoor(canvas,paint,door)
        render.renderDoor(canvas,paint,door2)
        render.renderCoins(canvas,paint,coins)
    }

}