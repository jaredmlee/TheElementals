package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint
import com.jaredlee.theelementals.levels.*
import kotlin.collections.ArrayList

class TheElementals( val width: Float, val height: Float) : GameObject() {
    private val numCols = 7;
    private val cellSize = width.toInt() / numCols;
    private val numRows: Int = (height - 50).toInt() / cellSize;
    private val level1: Level1;
    private val level2: Level2;
    private val level3: Level3;
    private val level4: Level4;
    private val level5: Level5;
    private val storeScreen: StoreScreen

    init{
        state["cellSize"] = cellSize
        state["numRows"] = numRows
        state["turn"] = "player"
        state["level"] = 1
        state["playerPos"] = Location(0f,12f)
        state["doorPos"] = Location(6f,2f)
        state["LockedDoorPos"] = Location(100f,100f)
        state["doorPos2"] = Location(1f,0f)
        state["playerHealth"] = 5
        state["swordsmanPos"] = Location(0f,5f)
        state["swordsmanHealth"] = 2
        state["coins"] = 0
        state["storePos"] = Location(100f,100f)
        val emptyArray : ArrayList<Location> = ArrayList()
        state["wallLocs"] = emptyArray
        level2 = Level2(this)
        level3 = Level3(this)
        level4 = Level4(this)
        level5 = Level5(this)
        storeScreen = StoreScreen(this)
        level1 = Level1(this) // level one has to be the last to initialize so the init values are
        //correct when game is launched
    }
    override fun update(deltaTime: Long){
        val level: Int = state["level"]
        if(level == 1){
            level1.doFrame(deltaTime)
            }
        else if (level == 2){
            level2.doFrame(deltaTime)
        }
        else if (level == 3){
            level3.doFrame(deltaTime)
        }
        else if (level == 4){
            level4.doFrame(deltaTime)
        }
        else if(level == 5){
            level5.doFrame(deltaTime)
        }
        else if(level==1000){
            storeScreen.update(deltaTime)
        }
    }
    override fun render(canvas: Canvas, paint: Paint){
        val level: Int = state["level"]
        if (level == 1){
            level1.renderLevel1(canvas,paint)
        }
        else if (level == 2){
            level2.renderLevel2(canvas,paint)
        }
        else if (level == 3){
            level3.renderLevel2(canvas,paint)
        }
        else if (level == 4){
            level4.renderLevel2(canvas,paint)
        }
        else if(level == 5){
            level5.renderLevel5(canvas,paint)
        }
        else if(level == 1000){
            storeScreen.render(canvas,paint)
        }
    }
}