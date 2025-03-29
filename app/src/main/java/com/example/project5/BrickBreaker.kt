package com.example.project5

import android.graphics.Rect
import androidx.transition.Visibility

class BrickBreaker {

    // The brick are stored in a 2D List
    private var bricks : MutableList<MutableList<Brick>> = mutableListOf(mutableListOf(), mutableListOf(),
        mutableListOf(), mutableListOf())

    // Each Brick has a Rect, status, ...
    inner class Brick (val rect: Rect, val isVisible: Boolean) {
        fun getVisibility() : Boolean {
            return this.isVisible
        }
    }

    fun addBrick(rect: Rect, isVisible: Boolean, row: Int, col: Int) {
        bricks[row].add(Brick(rect, isVisible))
    }

    fun getBrick(row : Int, col : Int) : Brick {
        return bricks[row][col]
    }

    fun getVisibility (row: Int, col: Int) : Boolean {
        return bricks[row][col].getVisibility()
    }

    // Some kind of loop function to check each block to see if it was hit

}