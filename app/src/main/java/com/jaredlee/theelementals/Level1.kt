package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.collections.ArrayList

class Level1(val game: GameObject): GameObject() {
    private var numCols = 7
    private var numRows = 13
    var player : GameObject
    var swordsman : GameObject
    var door: GameObject
    var door2: GameObject
    var coins: GameObject
    var key: GameObject
    private var floorTiles: ArrayList<CastleFloor> = ArrayList()
    private var walls: ArrayList<Wall> = ArrayList()
    private var wallLocs: ArrayList<Location> = ArrayList()
    init{
        player = Player(game)
        player.state["coords"] = Location(0f,12f)
        player.state["alive"] = true
        swordsman = Swordsman(game)
        swordsman.state["alive"] = true
        swordsman.state["coords"] = Location(0f,5f)
        door = Door(game)
        door.state["coords"] = Location(6f,2f)
        door2 = Door(game)
        door2.state["coords"] = Location(1f,0f)
        player.state["doorLevel"] = 2
        player.state["doorLevel2"] = 4
        coins = Coins(game)
        key = Key(game)
        key.state["coords"] = Location(1f,3f)
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val floor = CastleFloor(game)
                floor.state["coords"] = Location(j.toFloat(), i.toFloat())
                floorTiles.add(floor)
            }
        }
        for (i in 0 until 5){
            val wall = Wall(game)
            wall.state["coords"] = Location(2f,i.toFloat())
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 3){
            val wall = Wall(game)
            wall.state["coords"] = Location(i+4f,4f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        for (i in 0 until 2){
            val wall = Wall(game)
            wall.state["coords"] = Location(i+0f,4f)
            wallLocs.add(wall.state["coords"])
            walls.add(wall)
        }
        game.state["wallLocs"] = wallLocs

    }
    fun resetGameStates(){
        game.state["wallLocs"] = wallLocs
        game.state["swordsmanHealth"]  = 2
        swordsman.state["coords"] = Location(0f,5f)//for some reason it isn't actually changing
        swordsman.state["alive"] = true                  //the swordsmans states here, figure out way to fix
        game.state["doorPos"] = Location(6f,2f)
        game.state["doorPos2"] = Location(1f,0f)
        game.state["swordsmanPos"] = Location(0f,5f)
    }
    fun doFrame(deltaTime: Long){
        player.update(deltaTime)
        swordsman.update(deltaTime)
    }
    fun renderLevel1(canvas: Canvas, paint: Paint){
        val render = Render()
        render.renderFloor(canvas,paint,floorTiles)
        render.renderWalls(canvas,paint,walls)
        render.renderPlayer(canvas,paint, player)
        render.renderSwordsman(canvas,paint,swordsman)
        render.renderDoor(canvas,paint,door)
        render.renderDoor(canvas,paint,door2)
        render.renderKey(canvas,paint,key)
        render.renderCoins(canvas,paint,coins)

    }
}