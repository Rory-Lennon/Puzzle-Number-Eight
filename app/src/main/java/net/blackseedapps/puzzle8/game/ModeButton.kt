package net.blackseedapps.puzzle8.game

import android.graphics.*
import net.blackseedapps.puzzle8.MainActivity

class ModeButton() {
    private val LOG_TAG = "ModeButton "
    var mInset = 0F
    var mMode = TravelMode.MODE333
    val mRectButton = RectF()
    val mRectInset = RectF()
    val mPaintButton = Paint()
    val mPaintHighlight = Paint()
    val mPaintInset = Paint()
    val mRectBitmap = RectF()

    fun surfaceChanged(){
        mPaintButton.style = Paint.Style.STROKE
        mPaintButton.color = Color.YELLOW
        mPaintButton.strokeWidth = mInset
        mPaintHighlight.style = Paint.Style.FILL
        mPaintInset.style = Paint.Style.FILL
        val bw = mRectButton.height() * 0.6F
        val midw = (mRectButton.left + mRectButton.right) / 2F
        mRectBitmap.left = midw - bw / 2F
        mRectBitmap.right = midw + bw / 2F
        val midb = (mRectButton.top + mRectButton.bottom) / 2F
        mRectBitmap.bottom = midb + bw / 2F
        mRectBitmap.top = midb - bw / 2F
//        if(mMode == TravelMode.CIRCLE333) mBm = MainActivity.sImage333
    }
    fun setInset(inset : Float) {
        mInset = inset
        mRectButton.inset(inset, inset)
        mRectInset.top = mRectButton.top
        mRectInset.bottom = mRectButton.bottom
        mRectInset.left = mRectButton.left
        mRectInset.right = mRectButton.right
        mRectInset.inset(inset * 2F, inset * 2F)
    }
    fun draw(canvas: Canvas){
        if(Game.sTravelMode == mMode) mPaintHighlight.color = Color.YELLOW
//        if(Game.sTravelMode == mMode) mPaintHighlight.color = Color.rgb(150, 150, 150)
        else mPaintHighlight.color = Color.rgb(175, 175, 175)
        canvas.drawRoundRect(mRectButton, mInset * 4F, mInset * 4F, mPaintHighlight)

        if(Game.sTravelMode == mMode) mPaintInset.color = Color.rgb(0, 125, 255)
        else mPaintInset.color = Color.BLUE
        canvas.drawRoundRect(mRectInset, mInset * 2F, mInset * 2F, mPaintInset)

        var bm: Bitmap
        bm = MainActivity.sImage333
        if(mMode == TravelMode.MODE000)bm = MainActivity.sImage000
        if(mMode == TravelMode.MODE888)bm = MainActivity.sImage888
        if(mMode == TravelMode.MODEPAUSE){
            if(GameViewModel.sGameState == GameState.PAUSED) bm = MainActivity.sImagePlay
            else bm = MainActivity.sImagePause
        }
        canvas.drawBitmap(bm, null, mRectBitmap, null)
    }
}