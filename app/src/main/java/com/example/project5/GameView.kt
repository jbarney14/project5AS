package com.example.project5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View

class GameView : View {

    private lateinit var paint: Paint
    private lateinit var brickBreaker: BrickBreaker

    constructor(context : Context, width : Int, height : Int) : super(context) {

        paint = Paint( )
        paint.strokeWidth = 25f
        paint.isAntiAlias = true

        brickBreaker = BrickBreaker()

        val rows = 4
        val cols = 6
        val width = width/cols
        val height = 50

        // Creates a Rect for each block
        for (row in 0..<rows)
            // do some thing for 4 rows
            for (col in 0..<cols) {

                val brickLeft = col * width
                val brickTop = (row * height) + 20
                val brickRight = brickLeft + width
                val brickBottom = (brickTop + height) + 20
                val rect = Rect(brickLeft, brickTop, brickRight, brickBottom)

                // Print these to be sure they're adjacent
                if (row == 0) {
                    Log.w("MainActivity", "The horizontal is $brickLeft-$brickRight")
                    Log.w("MainActivity", "The vertical is $brickTop-$brickBottom")
                }

                brickBreaker.addBrick(rect,true, row, col)
            }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Makes ball
        canvas.drawCircle( 550f, 500f, 10f, paint )
        // Makes paddle
        canvas.drawLine( 470f, 2100f, 630f, 2100f, paint)

        paint.strokeWidth = 50f
        val colors = listOf(listOf(Color.RED, Color.BLUE), listOf(Color.YELLOW, Color.MAGENTA), listOf(Color.GREEN, Color.GRAY), listOf(Color.BLACK, Color.CYAN))

        // Paints colors on top of the Rects created for the blocks above
        for (row in 0..3) {
            for (col in 0 .. 5) {
                val brick = brickBreaker.getBrick(row, col)

                if (brick.isVisible) {
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