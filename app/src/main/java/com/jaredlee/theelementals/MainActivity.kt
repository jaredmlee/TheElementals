package com.jaredlee.theelementals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playButton : Button = findViewById<Button?>(R.id.playButton).apply {
            setOnClickListener {
                setContentView(GameView(this@MainActivity))
            }
        }
    }

}