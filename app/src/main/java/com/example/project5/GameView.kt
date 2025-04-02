package com.example.project5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import android.view.View

class GameView : View {

    private lateinit var paint: Paint
    private lateinit var brickBreaker: BrickBreaker

    private var rows : Int = 0
    private var cols : Int = 0

    constructor(context : Context, width : Int, height : Int) : super(context) {

        paint = Paint( )
        paint.strokeWidth = 25f
        paint.isAntiAlias = true

        // Make the ball and paddle Rects
        //canvas.drawCircle( 550f, 500f, 10f, paint )
        val x = 550f
        val y = 500f
        val radius = 15f
        val ballRect = RectF(x-radius, y-radius, x+radius, y+radius )
        // Makes paddle
        //canvas.drawLine( 470f, 2100f, 630f, 2100f, paint)
        //val paddleRect = RectF(470f, 2100f, 630f, 2100f)
        val paddleRect = RectF(470f, 1500f, 630f, 1500f)

        //rows = 4
        rows = 1
        cols = 6
        val width = width/cols
        val height = 50

        brickBreaker = BrickBreaker(rows, ballRect, paddleRect)

        // Creates a Rect for each block
        for (row in 0..<rows)
            // do some thing for 4 rows
            for (col in 0..<cols) {

                // Makes bricks
                val brickLeft = col * width
                val brickTop = (row * height) + 20
                val brickRight = brickLeft + width
                val brickBottom = (brickTop + height) + 20
                val rect = Rect(brickLeft, brickTop, brickRight, brickBottom)

                brickBreaker.addBrick(rect,false, row, col)
            }
    }

    fun getGame() : BrickBreaker {
        return brickBreaker
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 25f
        paint.color = Color.CYAN

        // Makes ball
        val ballRect = brickBreaker.getBallRect()
        val radius = brickBreaker.getRadius()
        //canvas.drawCircle( 550f, 500f, 10f, paint )
        canvas.drawCircle( (ballRect.left+radius), ballRect.top+radius, 10f, paint )

        // Makes paddle
        val paddleRect = brickBreaker.getPaddleRect()
        //canvas.drawLine( 470f, 2100f, 630f, 2100f, paint)
        canvas.drawLine( paddleRect.left, paddleRect.top, paddleRect.right, paddleRect.bottom, paint)

        // Width the makes lines the thickness of bricks
        paint.strokeWidth = 50f
        val colors = listOf(listOf(Color.RED, Color.BLUE), listOf(Color.YELLOW, Color.MAGENTA),
            listOf(Color.GREEN, Color.GRAY), listOf(Color.BLACK, Color.CYAN))

        // Paints colors on top of the Rects created for the blocks
        for (row in 0..rows-1) {
            for (col in 0 .. cols-1) {
                val brick = brickBreaker.getBrick(row, col)

                if (!brick.isHit) {
                    paint.color = colors[row][col%2]

                    val r = brick.rect
                    val left = r.left.toFloat()
                    val right = r.right.toFloat()
                    val top = r.top.toFloat()
                    val bottom = r.bottom.toFloat()

                   // The blocks are drawn by using a thick line (size 50f)
                    canvas.drawLine(left, top, right, top, paint)
                }
            }
        }


    }
}