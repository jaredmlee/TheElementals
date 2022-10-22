package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.collections.ArrayList

class Level2(private val game: GameObject): GameObject() {
    private var numCols = 7
    private var cellSize = 102
    private var numRows = 13
    var player : GameObject
    var swordsman : GameObject
    var door: GameObject
    var door2: GameObject
    var coins: GameObject
    var lockedDoor: GameObject
    private var floorTiles: ArrayList<CastleFloor> = ArrayList()
    private var wallLocs: ArrayList<Location> = ArrayList()
    private var walls: ArrayList<Wall> = ArrayList()
    init{
        val gameState = State()
        gameState["cellsize"] = cellSize
        player = Player(game)
        player.state["coords"] = Location(1f,2f)
        player.state["alive"] = true
        swordsman = Swordsman(game)
        swordsman.state["alive"] = true
        swordsman.state["coords"] = Location(0f,11f)
        door = Door(game);
        door.state["coords"] = Location(0f,2f)
        door2 = Door(game)
        door2.state["coords"] = Location(6f,0f)
        player.state["doorLevel"] = 1
        player.state["doorLevel2"] = 3
        lockedDoor = LockedDoor(game)
        lockedDoor.state["coords"] = Location(6f,10f)
        player.state["lockedDoorLevel"] = 5
        coins = Coins(game)
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val floor = CastleFloor(game)
                floor.state["coords"] = Location(j.toFloat(), i.toFloat())
                floorTiles.add(floor)
            }
        }
        for (i in 0 until 3){
            val wall = Wall(game)
            wall.state["coords"] = Location(i+0f,9f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 3){
            val wall = Wall(game)
            wall.state["coords"] = Location(i+4f,9f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 7){
            val wall = Wall(game)
            wall.state["coords"] = Location(2f,i+0f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 7){
            val wall = Wall(game)
            wall.state["coords"] = Location(3f,i+0f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 7){
            val wall = Wall(game)
            wall.state["coords"] = Location(4f,i+0f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        game.state["wallLocs"] = wallLocs
    }
    fun resetGameStates(){
        game.state["wallLocs"] = wallLocs
        swordsman.state["coords"] = Location(0f,11f)
        game.state["swordsmanHealth"]  = 2
        swordsman.state["alive"] = true
        game.state["doorPos"] = Location(0f,2f)
        game.state["doorPos2"] = Location(6f,0f)
        game.state["LockedDoorPos"] = Location(6f,10f)
        game.state["swordsmanPos"] = Location(0f,11f)
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
        render.renderKey(canvas,paint,door2)
        render.renderLockedDoor(canvas,paint,lockedDoor)
        render.renderCoins(canvas,paint,coins)
    }
}