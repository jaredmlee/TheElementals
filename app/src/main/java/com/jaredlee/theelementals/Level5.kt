package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint

class Level5(private val game: GameObject): GameObject() {
    private var numCols = 7
    private var numRows = 13
    var player: GameObject
    var swordsman: GameObject
    var coins: GameObject
    var store: GameObject
    private var floorTiles: ArrayList<Grass> = ArrayList()
    init{
        player = Player(game)
        player.state["coords"] = Location(0f,10f)
        player.state["alive"] = true
        swordsman  = Swordsman(game)
        swordsman.state["coords"] = Location(4f,10f)
        swordsman.state["alive"] = true
        coins = Coins(game)
        store = Store(game)
        store.state["coords"] = Location(2f,8f)

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                val floor = Grass(game)
                floor.state["coords"] = Location(j.toFloat(), i.toFloat())
                floorTiles.add(floor)

            }
        }
    }
    fun resetGameStates(){
        val emptyArray : ArrayList<Location> = ArrayList()
        game.state["wallLocs"] = emptyArray
        swordsman.state["coords"] = Location(4f,10f)
        game.state["swordsmanHealth"]  = 2
        swordsman.state["alive"] = true
        game.state["swordsmanPos"] = Location(4f,10f)
        game.state["storePos"] = Location(2f,8f)
    }
    fun doFrame(deltaTime: Long){
        player.update(deltaTime)
        swordsman.update(deltaTime)
    }
    fun renderLevel5(canvas: Canvas, paint: Paint){
        val render = Render()
        render.renderGrassFloor(canvas,paint,floorTiles)
        render.renderPlayer(canvas,paint, player)
        render.renderSwordsman(canvas,paint,swordsman)
        render.renderStore(canvas,paint,store)
        render.renderCoins(canvas,paint,coins)

    }
}