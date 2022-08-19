package net.blackseedapps.puzzle8.menu

import android.graphics.*
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.game.Game02
import net.blackseedapps.puzzle8.game.Game01
import net.blackseedapps.puzzle8.game.Game03

class MeunSelectTrack() : Menu() {

    val mRectButtonSelect01 = RectF()
    val mRectButtonSelect02 = RectF()
    val mRectButtonSelect03 = RectF()

    val mGame01 = Game01()
    val mGame02 = Game02()
    val mGame03 = Game03()

    override fun surfaceChanged() {
        super.surfaceChanged()
        mPaintMenuFill.color = Color.rgb(150, 0, 0)

        mRectButtonSelect01.top = GameFragment.sHeight * 0.15F
        mRectButtonSelect01.bottom = GameFragment.sHeight * 0.35F
        mRectButtonSelect01.left = GameFragment.sWidth * 0.1F
        mRectButtonSelect01.right = GameFragment.sWidth * 0.9F

        mRectButtonSelect02.top = GameFragment.sHeight * 0.4F
        mRectButtonSelect02.bottom = GameFragment.sHeight * 0.6F
        mRectButtonSelect02.left = GameFragment.sWidth * 0.1F
        mRectButtonSelect02.right = GameFragment.sWidth * 0.9F

        mRectButtonSelect03.top = GameFragment.sHeight * 0.65F
        mRectButtonSelect03.bottom = GameFragment.sHeight * 0.85F
        mRectButtonSelect03.left = GameFragment.sWidth * 0.1F
        mRectButtonSelect03.right = GameFragment.sWidth * 0.9F

        mGame01.mRadius = GameFragment.sHeight * 0.05F
        mGame01.mTopPos.x = GameFragment.sWidth * 0.25F
        mGame01.mTopPos.y = GameFragment.sHeight * 0.175F
        mGame01.surfaceChanged()

        mGame02.mRadius = GameFragment.sHeight * 0.05F
        mGame02.mTopPos.x = GameFragment.sWidth  * 0.25F
        mGame02.mTopPos.y = GameFragment.sHeight * 0.425F
        mGame02.surfaceChanged()

        mGame03.mRadius = GameFragment.sHeight * 0.05F
        mGame03.mTopPos.x = GameFragment.sWidth  * 0.25F
        mGame03.mTopPos.y = GameFragment.sHeight * 0.675F
        mGame03.surfaceChanged()
    }
    fun draw(canvas: Canvas) {

        val rectAlphaBand = RectF()
        rectAlphaBand.left = 0F
        rectAlphaBand.right = GameFragment.sWidth
        rectAlphaBand.top = 0F
        rectAlphaBand.bottom = GameFragment.sHeight
        mPaintMenuFill.alpha = 200
        canvas.drawRect(rectAlphaBand, mPaintMenuFill)

        val rectTextBounds = Rect()
        val strGameOver  = "SELECT TRACK"
        var centerX = GameFragment.sWidth / 2F
        var centerY = GameFragment.sHeight * 0.075F
        mPaintMenuText.getTextBounds(strGameOver, 0, strGameOver.length, rectTextBounds)
        canvas.drawText(strGameOver,  centerX - rectTextBounds.width() / 2F, centerY + rectTextBounds.height() / 2F, mPaintMenuText)


        canvas.drawRoundRect(mRectButtonSelect01, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonSelect01, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        mGame01.drawFromMenu(canvas)
        val textLeft = GameFragment.sWidth * 0.4F
        var textV = GameFragment.sHeight * 0.2F
        val strTrack01  = "TRACK 1"
        mPaintMenuTextSmall.getTextBounds(strTrack01, 0, strTrack01.length, rectTextBounds)
        canvas.drawText(strTrack01,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        textV = GameFragment.sHeight * 0.25F
        val strRange01  = "SCORE RANGE: " + mGame01.mMaxScore.toString()
        mPaintMenuTextSmall.getTextBounds(strRange01, 0, strRange01.length, rectTextBounds)
        canvas.drawText(strRange01,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        val strHigh01  = "HIGH SCORE: " + mGame01.getHighScore().toString()
        mPaintMenuTextSmall.getTextBounds(strHigh01, 0, strHigh01.length, rectTextBounds)
        textV = GameFragment.sHeight * 0.3F
        if(mGame01.getHighScore() > 0){
            canvas.drawText(strHigh01, textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        }

        canvas.drawRoundRect(mRectButtonSelect02, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonSelect02, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        mGame02.drawFromMenu(canvas)
        textV = GameFragment.sHeight * 0.45F
        val strTrack02  = "TRACK 2"
        mPaintMenuTextSmall.getTextBounds(strTrack02, 0, strTrack02.length, rectTextBounds)
        canvas.drawText(strTrack02,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        textV = GameFragment.sHeight * 0.5F
        val strRange02  = "SCORE RANGE: " + mGame02.mMaxScore.toString()
        mPaintMenuTextSmall.getTextBounds(strRange02, 0, strRange02.length, rectTextBounds)
        canvas.drawText(strRange02,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        val strHigh02  = "HIGH SCORE: " + mGame02.getHighScore().toString()
        mPaintMenuTextSmall.getTextBounds(strHigh02, 0, strHigh02.length, rectTextBounds)
        textV = GameFragment.sHeight * 0.55F
        if(mGame02.getHighScore() > 0){
            canvas.drawText(strHigh02, textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        }

        canvas.drawRoundRect(mRectButtonSelect03, mInset * 4F, mInset * 4F, mPaintButtonBackground)
        canvas.drawRoundRect(mRectButtonSelect03, mInset * 4F, mInset * 4F, mPaintButtonOutline)
        mGame03.drawFromMenu(canvas)
        textV = GameFragment.sHeight * 0.7F
        val strTrack03  = "TRACK 3"
        mPaintMenuTextSmall.getTextBounds(strTrack03, 0, strTrack03.length, rectTextBounds)
        canvas.drawText(strTrack03,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        textV = GameFragment.sHeight * 0.75F
        val strRange03  = "SCORE RANGE: " + mGame03.mMaxScore.toString()
        mPaintMenuTextSmall.getTextBounds(strRange03, 0, strRange03.length, rectTextBounds)
        canvas.drawText(strRange03,  textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        val strHigh03  = "HIGH SCORE: " + mGame03.getHighScore().toString()
        mPaintMenuTextSmall.getTextBounds(strHigh03, 0, strHigh03.length, rectTextBounds)
        textV = GameFragment.sHeight * 0.8F
        if(mGame03.getHighScore() > 0){
            canvas.drawText(strHigh03, textLeft, textV + rectTextBounds.height() / 2F, mPaintMenuTextSmall)
        }
        drawLogo(canvas)
    }
}