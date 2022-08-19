package net.blackseedapps.puzzle8.menu

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

open class Menu {

    var mPaintMenuText = Paint()
    var mPaintMenuTextSmall = Paint()
    var mPaintMenuLine = Paint()
    var mPaintMenuFill = Paint()
    var mPaintButtonBackground = Paint()
    var mPaintButtonOutline = Paint()
    var mPaintButtonText = Paint()
    var mPaintLogo = Paint()

    var mInset = 0F

    open fun surfaceChanged() {
        mInset = GameFragment.sHeight / 200F
        mPaintMenuText.color = Color.YELLOW
        mPaintMenuText.style = Paint.Style.FILL_AND_STROKE
        mPaintMenuText.typeface = MainActivity.sTypefaceScore
        mPaintMenuText.setTextSize(GameFragment.sHeight / 25F)
        mPaintMenuTextSmall.color = Color.YELLOW
        mPaintMenuTextSmall.style = Paint.Style.FILL_AND_STROKE
        mPaintMenuTextSmall.typeface = MainActivity.sTypefaceScore
        mPaintMenuTextSmall.setTextSize(GameFragment.sHeight / 45F)
        mPaintMenuLine.style = Paint.Style.STROKE
        mPaintMenuLine.color = Color.WHITE
        mPaintMenuLine.strokeWidth = GameFragment.sHeight / 200F
        mPaintMenuLine.style = Paint.Style.FILL
        mPaintMenuLine.color = Color.rgb(255, 255, 255)
        mPaintButtonBackground.style = Paint.Style.FILL
        mPaintButtonBackground.color = Color.rgb(0, 0, 255)
        mPaintButtonOutline.style = Paint.Style.STROKE
        mPaintButtonOutline.color = Color.rgb(175, 175, 175)
        mPaintButtonOutline.strokeWidth = mInset * 2F
        mPaintButtonText.color = Color.YELLOW
        mPaintButtonText.style = Paint.Style.FILL_AND_STROKE
        mPaintButtonText.typeface = MainActivity.sTypefaceScore
        mPaintButtonText.setTextSize(GameFragment.sHeight / 35F)
        mPaintMenuFill.style = Paint.Style.FILL
        mPaintLogo.color = Color.YELLOW
        mPaintLogo.style = Paint.Style.FILL
        mPaintLogo.typeface = MainActivity.sTypefaceLogo
        mPaintLogo.setTextSize(GameFragment.sHeight / 20F)
    }
    fun drawLogo(canvas: Canvas) {
//        val path = Path()
//        path.moveTo(GameFragment.sWidth * 0.95F, GameFragment.sHeight * 0.7F)
//        path.lineTo(GameFragment.sWidth * 0.95F, GameFragment.sHeight * 0.05F)
//        path.close();
        val rectTextBounds = Rect()
        val strLogo  = "Puzzle Number Eight"
        mPaintLogo.getTextBounds(strLogo, 0, strLogo.length, rectTextBounds)
//        canvas.drawTextOnPath(strLogo, path, 0F, 0F, mPaintLogo)
        val px = GameFragment.sWidth * 0.5F - rectTextBounds.width() / 2F
        val py = GameFragment.sHeight * 0.95F
        canvas.drawText(strLogo, px, py, mPaintLogo)
    }
}