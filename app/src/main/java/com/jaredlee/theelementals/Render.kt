package com.jaredlee.theelementals

import android.graphics.Canvas
import android.graphics.Paint
import com.jaredlee.theelementals.gameObjects.CastleFloor
import com.jaredlee.theelementals.gameObjects.Grass
import com.jaredlee.theelementals.gameObjects.Wall
import java.util.ArrayList

class Render{


    fun renderFloor(canvas : Canvas, paint : Paint, floorTiles: ArrayList<CastleFloor>) {
        floorTiles.forEach { floor ->
            canvas.save()
            floor.render(canvas, paint)
            paint.reset()
            canvas.restore()
        }
    }
    fun renderPlayer(canvas: Canvas, paint: Paint, player: GameObject){
        canvas.save()
        player.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderSwordsman(canvas: Canvas, paint: Paint, swordsman: GameObject){
        canvas.save()
        swordsman.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderDoor(canvas: Canvas, paint: Paint, door: GameObject){
        canvas.save()
        door.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderWalls(canvas : Canvas, paint : Paint,walls: ArrayList<Wall>) {
        walls.forEach { wall ->
            canvas.save()
            wall.render(canvas, paint)
            paint.reset()
            canvas.restore()
        }
    }
    fun renderCoins(canvas: Canvas, paint: Paint, coins: GameObject){
        canvas.save()
        coins.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderLockedDoor(canvas:Canvas,paint: Paint,lockedDoor: GameObject){
        canvas.save()
        lockedDoor.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderGrassFloor(canvas : Canvas, paint : Paint, floorTiles: ArrayList<Grass>) {
        floorTiles.forEach { floor ->
            canvas.save()
            floor.render(canvas, paint)
            paint.reset()
            canvas.restore()
        }
    }
    fun renderStore(canvas:Canvas,paint: Paint,store: GameObject){
        canvas.save()
        store.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
    fun renderKey(canvas:Canvas,paint: Paint,key: GameObject){
        canvas.save()
        key.render(canvas,paint)
        paint.reset()
        canvas.restore()
    }
}