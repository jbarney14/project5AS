package com.example.project5

import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import androidx.transition.Visibility

class BrickBreaker {

    /*
    private var ballCenter : Point = Point(550, 500)
    private var ballRadius : Float = 10f
    // Start is the left and top
    private var paddleStart : Point = Point(470,2100)
    // End is right and bottom
    private var paddleEnd : Point = Point(630, 2100)
     */
    private var ballRadius = 10f

    // Possibly will use these fields later
    private var deltaTime = 0
    private var ballGoingDown : Boolean = false
    private var ballGoingLeft : Boolean = false
    private var ballSpeedX = 0f
    private var ballSpeedY = 0f
    private var paddleSpeed = 0f
    private var blocksLeft = 24


    //private var bricks: MutableList<MutableList<Brick>>? = mutableListOf(mutableListOf(), mutableListOf(),
      //  mutableListOf(), mutableListOf())


    private var bricks: MutableList<MutableList<Brick>>? = mutableListOf()

    private var ballRect: RectF? = null
    private var paddleRect: RectF? = null

    constructor(rows: Int, bRect: RectF?, pRect: RectF?) {

        for (i in 0..rows-1) {
            bricks!!.add(mutableListOf())
        }

        ballRect = bRect
        paddleRect = pRect
    }


    // Each Brick has a Rect, status, ...
    inner class Brick (val rect: Rect, var isHit: Boolean) {
        fun getVisibility() : Boolean {
            return this.isHit
        }
    }

    fun update() {
        moveBall()

        var gameWonFlag = true

        for (row in bricks!!) {
            for (brick in row) {
                val brickRectF = RectF(
                    brick.rect.left.toFloat(), brick.rect.top.toFloat(),
                    brick.rect.right.toFloat(), brick.rect.bottom.toFloat()
                )

                if (!brick.isHit) {
                    gameWonFlag = false
                }

                if (RectF.intersects(ballRect!!, brickRectF) && !brick.isHit) {
                    brick.isHit = true // Mark the brick as hit
                    Log.w("BrickBreaker", "collision!") //debug
                    MainActivity.level++
                    if (MainActivity.level > MainActivity.bestLevel) {
                        MainActivity.bestLevel = MainActivity.level
                        MainActivity.editor.putInt("best_level", MainActivity.bestLevel).apply()
                    }
                    ballSpeedY = -ballSpeedY
                    break
                }

            }
        }

        if (gameWon()) {
            val bricksHit = MainActivity.level
            Log.w("MainActivity", "Hit all blocks, $bricksHit")
        }

        // Some way of marking the end of the game
        checkWallsTouch()
        checkPaddleTouch()
    }

    fun gameWon() : Boolean {
        var flag = true
        for (row in bricks!!) {
            for (brick in row) {
                if (!brick.isHit) {
                    flag = false
                }
            }
        }
        return flag
    }

    fun startGame(direction: Boolean) {
        ballSpeedX = if (direction) 30f else -30f // Start at 45 degrees
        ballSpeedY = 30f
    }

    private fun moveBall() {
        ballRect?.offset(ballSpeedX, ballSpeedY)
    }

    fun movePaddle(xPosition: Float) {
        val paddleWidth = paddleRect?.width()

        val newLeft = (xPosition - paddleWidth!! / 2).coerceIn(0f, 1080f - paddleWidth!!)
        paddleRect?.offsetTo(newLeft, paddleRect!!.top) // Move paddle
    }

    fun checkWallsTouch() {

       if(ballRect!!.left < 0) {
           ballSpeedX = -ballSpeedX
       } else if ( ballRect!!.top < 0) {
           ballSpeedY = -ballSpeedY
       }else {
           if(ballRect!!.right > 1080) {
            ballSpeedX = -ballSpeedX
           }
       }
    }

    fun checkPaddleTouch(){
        if (RectF.intersects(ballRect!!, paddleRect!!)) {
            ballSpeedY = -ballSpeedY

            // Sound code
        }
    }

    fun addBrick(rect: Rect, isHit: Boolean, row: Int, col: Int) {
        bricks!![row].add(Brick(rect, isHit))
    }

    fun getBrick(row : Int, col : Int) : Brick {
        return bricks!![row][col]
    }

    fun getVisibility (row: Int, col: Int) : Boolean {
        return bricks!![row][col].getVisibility()
    }

    fun getPaddleRect() : RectF {
        return paddleRect!!
    }
    fun setPaddleRect(startX: Float, startY: Float, endX: Float, endY: Float) {
        paddleRect = RectF(startX, startY, endX, endY)
    }
    fun getBallRect() : RectF {
        return ballRect!!
    }
    fun setBallRect(startX: Float, startY: Float, endX: Float, endY: Float)
    {
        ballRect = RectF(startX, startY, endX, endY)
    }

    fun getRadius() : Float {
        return ballRadius
    }


}