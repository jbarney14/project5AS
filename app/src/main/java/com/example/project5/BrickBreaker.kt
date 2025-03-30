package com.example.project5

import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
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
    private var ballSpeed = 0f
    private var paddleSpeed = 0f
    private var blocksLeft = 24

    private var bricks: MutableList<MutableList<Brick>>? = mutableListOf(mutableListOf(), mutableListOf(),
        mutableListOf(), mutableListOf())

    private var ballRect: RectF? = null
    private var paddleRect: RectF? = null

    constructor(bRect: RectF?, pRect: RectF?) {

        ballRect = bRect
        paddleRect = pRect
    }


    // Each Brick has a Rect, status, ...
    inner class Brick (val rect: Rect, val isHit: Boolean) {
        fun getVisibility() : Boolean {
            return this.isHit
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

    /*
    fun getBallRadius(): Float {
        return ballRadius
    }
    fun getBallCenter(): Point {
        return ballCenter
    }
    fun setBallCenter(x: Int, y: Int) {
        ballCenter = Point(x,y)
    }
    fun paddleStart() : Point {
        return paddleStart
    }
    fun setPaddleStart(x: Int, y: Int) {
        paddleStart = Point(x,y)
    }
    fun getPaddleEnd() : Point {
        return paddleEnd
    }
    fun setPaddleEnd(x: Int, y: Int) {
        paddleEnd = Point(x,y)
    }

     */

    // Some kind of loop function to check each block to see if it was hit

}