package com.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false

    private lateinit var top: ImageView
    private lateinit var topEnd: ImageView
    private lateinit var topStart: ImageView

    private lateinit var center: ImageView
    private lateinit var centerEnd: ImageView
    private lateinit var centerStart: ImageView

    private lateinit var bottom: ImageView
    private lateinit var bottomEnd: ImageView
    private lateinit var bottomStart: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        center = findViewById(R.id.center)
        centerEnd = findViewById(R.id.center_end)
        centerStart = findViewById(R.id.center_start)

        top = findViewById(R.id.top)
        topEnd = findViewById(R.id.top_end)
        topStart = findViewById(R.id.top_start)

        bottom = findViewById(R.id.bottom)
        bottomEnd = findViewById(R.id.bottom_end)
        bottomStart = findViewById(R.id.bottom_start)

        val reset: Button = findViewById(R.id.button)
        reset.setOnClickListener {
            reset(center)
            reset(centerEnd)
            reset(centerStart)

            reset(top)
            reset(topEnd)
            reset(topStart)

            reset(bottom)
            reset(bottomEnd)
            reset(bottomStart)
        }

        configureBox(center)
        configureBox(centerEnd)
        configureBox(centerStart)

        configureBox(top)
        configureBox(topEnd)
        configureBox(topStart)

        configureBox(bottom)
        configureBox(bottomEnd)
        configureBox(bottomStart)

    }

    private fun reset(box: ImageView) {
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    private fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if(box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_baseline_circle_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.ic_baseline_clear_24)
                    isPlayer1 = true
                    box.tag = 2
                }
                if (playerWin(1)){
                    Toast.makeText(this@MainActivity, "Player 1 venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                } else if (playerWin(2)){
                    Toast.makeText(this@MainActivity, "Player 2 venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

    private fun playerWin(value: Int) : Boolean {
        if( (top.tag == value && center.tag == value && bottom.tag == value ) ||
            (topStart.tag == value && centerStart.tag == value && bottomStart.tag == value ) ||
            (topEnd.tag == value && centerEnd.tag == value && bottomEnd.tag == value ) ||

            (topStart.tag == value && top.tag == value && topEnd.tag == value ) ||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value ) ||
            (bottomStart.tag == value && bottom.tag == value && bottomEnd.tag == value ) ||

            (topStart.tag == value && center.tag == value && bottomEnd.tag == value ) ||
            (topEnd.tag == value && center.tag == value && bottomStart.tag == value )
        ) {
            return true
        }
        return false
    }
}