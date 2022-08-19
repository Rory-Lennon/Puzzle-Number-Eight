package net.blackseedapps.puzzle8.menu

import android.graphics.*
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

class MenuMain : Menu() {

    val mRectButtonPlay = RectF()
    val mRectButtonHow = RectF()
    val mRectButtonOptions = RectF()
    val mRectButtonExit = RectF()


    override fun surfaceChanged() {
        super.surfaceChanged()
        mPaintMenuFill.color = Color.rgb(125, 0, 0)
    }
    fun draw(canvas: Canvas) {
        val rectAlphaBand = RectF()
        rectAlphaBand.left = 0F
        rectAlphaBand.right = GameFragment.sWidth
        rectAlphaBand.top = 0F
        rectAlphaBand.bottom = GameFragment.sHeight

        mPaintMenuFill.alpha = 175
        canvas.drawRect(rectAlphaBand, mPaintMenuFill)

        val strPlay = "PLAY"
        val rectTextBounds = Rect()
        var centerX = GameFragment.sWidth / 2F
        var centerY = GameFragment.sHeight * 0.2F
        mPaintButtonText.getTextBounds(strPlay, 0, strPlay.length, rectTextBounds)
        mRectButtonPlay.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonPlay.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonPlay.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonPlay.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonPlay, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonPlay, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strPlay,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)

        val strHowToPlay = "HOW TO PLAY"
        centerY = GameFragment.sHeight * 0.3F
        mPaintButtonText.getTextBounds(strHowToPlay, 0, strHowToPlay.length, rectTextBounds)
        mRectButtonHow.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonHow.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonHow.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonHow.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonHow, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonHow, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strHowToPlay,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)

        val strGameOptions = "GAME OPTIONS"
        centerY = GameFragment.sHeight * 0.4F
        mPaintButtonText.getTextBounds(strGameOptions, 0, strGameOptions.length, rectTextBounds)
        mRectButtonOptions.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonOptions.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonOptions.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonOptions.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonOptions, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonOptions, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strGameOptions,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)

        val strExit = "EXIT"
        centerY = GameFragment.sHeight * 0.5F
        mPaintButtonText.getTextBounds(strExit, 0, strExit.length, rectTextBounds)
        mRectButtonExit.top = centerY - rectTextBounds.height() * 1.5F
        mRectButtonExit.bottom = centerY + rectTextBounds.height() * 1.5F
        mRectButtonExit.left = centerX - rectTextBounds.width() / 2F - rectTextBounds.height() * 1.5F
        mRectButtonExit.right = centerX + rectTextBounds.width() / 2F + rectTextBounds.height() * 1.5F
        canvas.drawRoundRect(mRectButtonExit, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonExit, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        canvas.drawText(strExit,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintButtonText)

        drawLogo(canvas)
    }

}