package com.jaredlee.theelementals

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.Choreographer
import android.view.MotionEvent
import android.view.View

class GameView (context: Context) : View(context), Choreographer.FrameCallback {
    private val paint = Paint()
    private var game: GameObject? = null
    var time: Long = 0;
    @SuppressLint("ClickableViewAccessibility")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        time = System.nanoTime()
        if(game==null) {
            game = TheElementals(width.toFloat(), height.toFloat())
            setOnTouchListener { _: View?, motionEvent: MotionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    println("I hath pushed down on the screen")
                    game?.addInput(motionEvent.x, motionEvent.y)
                    return@setOnTouchListener true
                }
                false
            }
            Choreographer.getInstance().postFrameCallback(this)
        }
    }
    override fun onDraw(canvas: Canvas){
        game?.render(canvas,paint)
    }
    override fun doFrame(l: Long) {
        val deltaT = l - time
        time = l
        game?.update(deltaT)
        Choreographer.getInstance().postFrameCallback(this)
        invalidate()
    }
}