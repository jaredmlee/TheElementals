package com.jaredlee.theelementals

import com.jaredlee.theelementals.levels.*

class ResetStates(val game: GameObject) {
    fun reset(level: Int){
        val level1 = Level1(game)
        val level2 = Level2(game)
        val level3 = Level3(game)
        val level4 = Level4(game)
        val level5 = Level5(game)
        if (level == 1){
            level1.resetGameStates()
        }
        if (level ==2 ){
            level2.resetGameStates()
        }
        if (level ==3 ){
            level3.resetGameStates()
        }
        if (level ==4 ){
            level4.resetGameStates()
        }
        if (level==5){
            level5.resetGameStates()
        }
    }
}