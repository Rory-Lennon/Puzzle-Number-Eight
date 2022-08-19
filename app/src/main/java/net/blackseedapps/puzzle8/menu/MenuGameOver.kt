package net.blackseedapps.puzzle8.menu

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.game.GameViewModel

class MenuGameOver : Menu() {

    override fun surfaceChanged() {
        super.surfaceChanged()
        mPaintMenuFill.color = Color.rgb(150, 0, 0)
    }
    fun draw(score : Int, highScore : Int, canvas: Canvas) {
        val rectAlphaBand = RectF()
        rectAlphaBand.left = 0F
        rectAlphaBand.right = GameFragment.sWidth
        rectAlphaBand.top = GameFragment.sHeight * 0.25F
        rectAlphaBand.bottom = GameFragment.sHeight * 0.45F

        mPaintMenuFill.alpha = 200
        canvas.drawRect(rectAlphaBand, mPaintMenuFill)
        canvas.drawLine(rectAlphaBand.left, rectAlphaBand.top, rectAlphaBand.right, rectAlphaBand.top, mPaintMenuLine)
        canvas.drawLine(rectAlphaBand.left, rectAlphaBand.bottom, rectAlphaBand.right, rectAlphaBand.bottom, mPaintMenuLine)

        val rectTextBounds = Rect()
        val strGameOver  = "GAME OVER"
        var centerX = GameFragment.sWidth / 2F
        var centerY = GameFragment.sHeight * 0.3F
        mPaintMenuText.getTextBounds(strGameOver, 0, strGameOver.length, rectTextBounds)
        canvas.drawText(strGameOver,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintMenuText)

        val strScore = "SCORE: " + score.toString()
        centerX = GameFragment.sWidth / 2F
        centerY = GameFragment.sHeight * 0.35F
        mPaintMenuText.getTextBounds(strScore, 0, strScore.length, rectTextBounds)
        canvas.drawText(strScore,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintMenuText)

        val strHighScore = "HIGH SCORE: " + highScore.toString()
        centerX = GameFragment.sWidth / 2F
        centerY = GameFragment.sHeight * 0.4F
        mPaintMenuText.getTextBounds(strHighScore, 0, strHighScore.length, rectTextBounds)
        if(highScore > 0) {
            canvas.drawText(
                strHighScore,
                centerX - rectTextBounds.width() / 2F,
                centerY + rectTextBounds.height() / 2F,
                mPaintMenuText
            )
        }
    }

}