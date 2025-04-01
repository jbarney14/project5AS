package com.example.project5

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView

    companion object {
        private var level: Int = 0
        private var bestLevel: Int = 0

        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun initializePreferences(context: Context) {
            sharedPreferences = context.getSharedPreferences("best_level", Context.MODE_PRIVATE)
            editor = sharedPreferences.edit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePreferences(this)
        bestLevel = MainActivity.sharedPreferences.getInt("best_level", 0)
        }

    private var gameStart : Int = 0

    fun updateModel() {

        var game : BrickBreaker = gameView.getGame()

        game.update()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        buildViewByCode( )
    }

    fun buildViewByCode() {
        var width : Int = resources.displayMetrics.widthPixels
        var height : Int = resources.displayMetrics.heightPixels
        /*
        var rectangle : Rect = Rect( 0, 0, 0, 0 )
        window.decorView.getWindowVisibleDisplayFrame( rectangle )
        Log.w( "MainActivity", "width = " + width )
        Log.w( "MainActivity", "height = " + height )
        Log.w( "MainActivity", "rectangle left = " + rectangle.left )
        Log.w( "MainActivity", "rectangle right = " + rectangle.right)
        Log.w( "MainActivity", "status bar height = " + rectangle.top )
        Log.w( "MainActivity", "rectangle bottom = " + rectangle.bottom)
        var statusBarHeight : Int = rectangle.top

         */

        // Do I need to put width and height back into the GameView?
        gameView = GameView( this, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        setContentView( gameView )
    }

    // Functions that implement click methods from interface

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var game: BrickBreaker = gameView.getGame()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val direction = Math.random() < 0.5 // Randomly choose left or right
                if (gameStart == 0) { // Ensure game starts only once
                    gameStart++
                    game.startGame(direction)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                game.movePaddle(event.x) // Move the paddle based on touch position
            }
        }
        return true
    }


}