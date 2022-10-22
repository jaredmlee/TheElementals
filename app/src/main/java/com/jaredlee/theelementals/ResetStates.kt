package com.jaredlee.theelementals

class ResetStates(val game: GameObject) {
    fun reset(level: Int){
        val level1 = Level1(game)
        val level2 = Level2(game)
        val level5 = Level5(game)
        if (level == 1){
            level1.resetGameStates()
        }
        if (level ==2 ){
            level2.resetGameStates()
        }
        if (level==5){
            level5.resetGameStates()
        }
    }
}