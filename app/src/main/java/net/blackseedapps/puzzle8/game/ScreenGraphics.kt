package net.blackseedapps.puzzle8.game

import android.graphics.*
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.menu.MenuGameOver
import net.blackseedapps.puzzle8.menu.MenuMain
import net.blackseedapps.puzzle8.menu.MenuPaused
import net.blackseedapps.puzzle8.menu.MeunSelectTrack

class ScreenGraphics() {
    private val LOG_TAG = "ScreenGraphics "

    val mPaintScore = Paint()
    val mPaintCircleFlash = Paint()
    val mPaintButtonGroup = Paint()
    val mPaintButtonBackground = Paint()

    val mPaintFuelGaugeBackground = Paint()
    val mPaintFuelGaugeOutside = Paint()
    val mPaintFuelGaugeInside = Paint()
    val mPaintFuelGaugeArcCyan = Paint()
    var mPaintFuelGaugeLine = Paint()
    var mPaintBarBand = Paint()
    var mWindowRect = RectF()
    val mButtonGroupRect = RectF()
    var mInset = 0F
    val mModeButton01 = ModeButton()
    val mModeButton02 = ModeButton()
    val mModeButton03 = ModeButton()
    val mModeButton04 = ModeButton()
    val mMenuGameOver = MenuGameOver()
    val mMenuMain = MenuMain()
    val mMenuPaused = MenuPaused()
    val mMenuSelectTrack = MeunSelectTrack()

    fun surfaceChanged(){
        mInset = GameFragment.sHeight / 200F
        mWindowRect.left = 0F
        mWindowRect.right = GameFragment.sWidth
        mWindowRect.top = 0F
        mWindowRect.bottom = GameFragment.sHeight
        mButtonGroupRect.left = GameFragment.sWidth * 0.3F
        mButtonGroupRect.right = GameFragment.sWidth * 0.9F
        mButtonGroupRect.top = GameFragment.sHeight * 0.7F
        mButtonGroupRect.bottom = GameFragment.sHeight * 0.88F
        mModeButton01.mMode = TravelMode.MODEPAUSE
        mModeButton02.mMode = TravelMode.MODE333
        mModeButton03.mMode = TravelMode.MODE888
        mModeButton04.mMode = TravelMode.MODE000
        mModeButton01.mRectButton.top = mButtonGroupRect.top
        mModeButton02.mRectButton.top = mButtonGroupRect.top
        mModeButton03.mRectButton.top = mButtonGroupRect.top
        mModeButton04.mRectButton.top = mButtonGroupRect.top
        mModeButton01.mRectButton.bottom = mButtonGroupRect.bottom
        mModeButton02.mRectButton.bottom = mButtonGroupRect.bottom
        mModeButton03.mRectButton.bottom = mButtonGroupRect.bottom
        mModeButton04.mRectButton.bottom = mButtonGroupRect.bottom
        mModeButton01.mRectButton.left = GameFragment.sWidth * 0.1F
        mModeButton02.mRectButton.left = GameFragment.sWidth * 0.3F
        mModeButton03.mRectButton.left = GameFragment.sWidth * 0.5F
        mModeButton04.mRectButton.left = GameFragment.sWidth * 0.7F
        mModeButton01.mRectButton.right = GameFragment.sWidth * 0.3F
        mModeButton02.mRectButton.right = GameFragment.sWidth * 0.5F
        mModeButton03.mRectButton.right = GameFragment.sWidth * 0.7F
        mModeButton04.mRectButton.right = GameFragment.sWidth * 0.9F
        mModeButton01.setInset(mInset)
        mModeButton02.setInset(mInset)
        mModeButton03.setInset(mInset)
        mModeButton04.setInset(mInset)
        mButtonGroupRect.inset(mInset, mInset)
        mPaintCircleFlash.style = Paint.Style.STROKE
        mPaintCircleFlash.color = Color.WHITE
        mPaintScore.color = Color.YELLOW
        mPaintScore.style = Paint.Style.FILL
        mPaintScore.typeface = MainActivity.sTypefaceScore
        mPaintScore.setTextSize(GameFragment.sHeight / 15F)
        mPaintButtonGroup.style = Paint.Style.FILL
        mPaintButtonGroup.color = Color.rgb(0, 125, 255)
        mPaintButtonBackground.style = Paint.Style.FILL
        mPaintButtonBackground.color = Color.rgb(0, 0, 255)
        mPaintFuelGaugeBackground.style = Paint.Style.FILL
        mPaintFuelGaugeBackground.color = Color.rgb(0, 125, 255)
        mPaintFuelGaugeOutside.style = Paint.Style.STROKE
        mPaintFuelGaugeOutside.color = Color.rgb(175, 175, 175)
        mPaintFuelGaugeOutside.strokeWidth = mInset * 2F
        mPaintFuelGaugeInside.style = Paint.Style.FILL
        mPaintFuelGaugeInside.color = Color.rgb(175, 175, 175)
        mPaintFuelGaugeArcCyan.style = Paint.Style.FILL
        mPaintFuelGaugeArcCyan.color = Color.CYAN
        mPaintFuelGaugeLine.style = Paint.Style.STROKE
        mPaintFuelGaugeLine.strokeWidth = GameFragment.sHeight / 200F
        mPaintFuelGaugeLine.color = Color.rgb(175, 175, 175)
        mPaintBarBand.style = Paint.Style.FILL
        mPaintBarBand.color =  Color.rgb(175, 175, 175)
        mModeButton01.surfaceChanged()
        mModeButton02.surfaceChanged()
        mModeButton03.surfaceChanged()
        mModeButton04.surfaceChanged()
        mMenuMain.surfaceChanged()
        mMenuPaused.surfaceChanged()
        mMenuGameOver.surfaceChanged()
        mMenuSelectTrack.surfaceChanged()
    }
    fun drawGradient(canvas: Canvas){
        val clr01 = Color.CYAN
        val clr02 = Color.BLUE
        val verticalGradient = LinearGradient(0F, 0F, 0F, GameFragment.sHeight, clr01, clr02, Shader.TileMode.CLAMP)
        val paintShader = Paint()
        paintShader.setShader(verticalGradient)
        val rectScreen = RectF()
        rectScreen.top = 0F
        rectScreen.bottom = GameFragment.sHeight
        rectScreen.left = 0F
        rectScreen.right = GameFragment.sWidth
        canvas.drawRect(rectScreen, paintShader)
    }
    fun drawButtons(canvas: Canvas){
        canvas.drawRoundRect(mButtonGroupRect, mInset * 4F, mInset * 4F, mPaintButtonGroup)
        mModeButton01.draw(canvas)
        mModeButton02.draw(canvas)
        mModeButton03.draw(canvas)
        mModeButton04.draw(canvas)
    }
    fun drawScore(score : Int, canvas: Canvas){
        val strScore = score.toString()
        val rectTextBounds = Rect()
        mPaintScore.getTextBounds(strScore, 0, strScore.length, rectTextBounds)
        var px = GameFragment.sWidth * 0.95F - rectTextBounds.width()
        var py = GameFragment.sHeight * 0.075F + rectTextBounds.height() / 2F
        canvas.drawText(strScore, px, py, mPaintScore)
    }
    fun drawLivesLeft(fraction : Float, canvas: Canvas){
        val sizeFuelGauge = GameFragment.sHeight * 0.1F
        val px = GameFragment.sWidth * 0.05F + sizeFuelGauge / 2F
        val py = GameFragment.sHeight * 0.075F
        val rectFuelGauge = RectF()
        rectFuelGauge.left = px - sizeFuelGauge / 2F
        rectFuelGauge.right = px + sizeFuelGauge / 2F
        rectFuelGauge.top = py - sizeFuelGauge / 2F
        rectFuelGauge.bottom = py + sizeFuelGauge / 2F
        val degs = -360F * fraction
        canvas.drawCircle(px, py, sizeFuelGauge / 2F, mPaintFuelGaugeArcCyan)
        canvas.drawArc(rectFuelGauge, 180F, degs, true, mPaintFuelGaugeBackground)
        canvas.drawCircle(px, py, sizeFuelGauge / 2F, mPaintFuelGaugeOutside)
        canvas.drawCircle(px, py, mInset * 5F, mPaintFuelGaugeInside)
        canvas.drawLine(px, py, px - (sizeFuelGauge / 2F), py, mPaintFuelGaugeLine)
    }
    fun drawBarBand(canvas: Canvas) {
        val py = GameFragment.sHeight * 0.075F
        val sizeBarBand = GameFragment.sHeight * 0.075F / 2F
        val rectBarBand = RectF()
        rectBarBand.left = 0F
        rectBarBand.right = GameFragment.sWidth
        rectBarBand.top = py - sizeBarBand
        rectBarBand.bottom = py + sizeBarBand
        canvas.drawRect(rectBarBand, mPaintBarBand)
    }
}