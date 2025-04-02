package com.example.project5

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer

class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView

    companion object {
        var level: Int = 0
        var bestLevel: Int = 0

        private lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        fun initializePreferences(context: Context) {
            sharedPreferences = context.getSharedPreferences("best_level", Context.MODE_PRIVATE)
            editor = sharedPreferences.edit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePreferences(this)
        bestLevel = MainActivity.sharedPreferences.getInt("best_level", 0)

        //var pooBuilder : SoundPool.Builder = SoundPool.Builder()
        //var pool : SoundPool = pooBuilder.build()
        //var paddleSoundId : Int = pool.load(this, R.raw.ball_bouncing, 1)
    }

    private var gameStart : Int = 0

    fun updateModel() {
        var game : BrickBreaker = gameView.getGame()
        game.update()

    }

    fun updateView() {
        gameView.postInvalidate()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        buildViewByCode( )
    }

    fun buildViewByCode() {
        var width : Int = resources.displayMetrics.widthPixels
        var height : Int = resources.displayMetrics.heightPixels


        // Do I need to put width and height back into the GameView?
        gameView = GameView( this, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        setContentView( gameView )

        var timer : Timer = Timer( )
        var task : GameTimerTask = GameTimerTask( this )
        timer.schedule( task, 0, 100 )
    }

    // Functions that implement click methods from interface

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var game: BrickBreaker = gameView.getGame()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //Log.w("MainActivity", "Click happened!")
                val direction = Math.random() < 0.5 // Randomly choose left or right
                if (gameStart == 0) { // Ensure game starts only once
                    gameStart++
                    game.startGame(direction)

                }
            }
            MotionEvent.ACTION_MOVE -> {
                //Log.w("MainActivity", "Movement of the mouse")
                game.movePaddle(event.x) // Move the paddle based on touch position
            }
        }
        return true
    }


}