package net.blackseedapps.puzzle8.menu

import android.graphics.*
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.game.GameViewModel

class MenuPaused() : Menu() {

    val mRectButtonResumeGame = RectF()
    val mRectButtonMainMenu = RectF()

    override fun surfaceChanged() {
        super.surfaceChanged()
        mPaintMenuFill.color = Color.rgb(150, 0, 0)
    }
    fun draw(score : Int, highScore : Int, canvas: Canvas) {
        val rectAlphaBand = RectF()
        rectAlphaBand.left = 0F
        rectAlphaBand.right = GameFragment.sWidth
        rectAlphaBand.top = GameFragment.sHeight * 0.15F
        rectAlphaBand.bottom = GameFragment.sHeight * 0.6F

        mPaintMenuFill.alpha = 200
        canvas.drawRect(rectAlphaBand, mPaintMenuFill)
        canvas.drawLine(rectAlphaBand.left, rectAlphaBand.top, rectAlphaBand.right, rectAlphaBand.top, mPaintMenuLine)
        canvas.drawLine(rectAlphaBand.left, rectAlphaBand.bottom, rectAlphaBand.right, rectAlphaBand.bottom, mPaintMenuLine)

        val rectTextBounds = Rect()
        val strGameOver  = "GAME PAUSED"
        var centerX = GameFragment.sWidth / 2F
        var centerY = GameFragment.sHeight * 0.225F
        mPaintMenuText.getTextBounds(strGameOver, 0, strGameOver.length, rectTextBounds)
        canvas.drawText(strGameOver,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintMenuText)

        val strHighScore = "HIGH SCORE: " + highScore.toString()
        centerX = GameFragment.sWidth / 2F
        centerY = GameFragment.sHeight * 0.3F
        mPaintMenuText.getTextBounds(strHighScore, 0, strHighScore.length, rectTextBounds)
        if(highScore > 0) {
            canvas.drawText(
                strHighScore,
                centerX - rectTextBounds.width() / 2F,
                centerY + rectTextBounds.height() / 2F,
                mPaintMenuText
            )
        }

        val strResumeGame = "RESUME GAME"
        centerX = GameFragment.sWidth / 2F
        centerY = GameFragment.sHeight * 0.4F
        mPaintButtonText.getTextBounds(strResumeGame, 0, strResumeGame.length, rectTextBounds)
        mRectButtonResumeGame.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonResumeGame.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonResumeGame.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonResumeGame.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonResumeGame, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonResumeGame, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strResumeGame,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)

        val strMainMenu = "MAIN MENU"
        centerX = GameFragment.sWidth / 2F
        centerY = GameFragment.sHeight * 0.5F
        mPaintButtonText.getTextBounds(strMainMenu, 0, strMainMenu.length, rectTextBounds)
        mRectButtonMainMenu.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonMainMenu.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonMainMenu.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonMainMenu.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonMainMenu, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonMainMenu, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strMainMenu,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)
    }
}