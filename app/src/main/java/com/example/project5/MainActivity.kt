package com.example.project5

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

}