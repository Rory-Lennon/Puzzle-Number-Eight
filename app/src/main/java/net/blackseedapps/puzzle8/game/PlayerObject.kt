package net.blackseedapps.puzzle8.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.view.MotionEvent
import net.blackseedapps.puzzle8.GameObject
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

class PlayerObject() : GameObject() {
    private val LOG_TAG = "PlayerObject "
    var mJumping = false
    init{
        mAnimateStepSize = 3
        mVelocity = 6F
    }
    override fun surfaceChanged() {
        super.surfaceChanged()
    }
    override fun getNextHeadTrackLink() : TrackLink {
        if(Game.sTravelMode == TravelMode.MODE000) {
            val trackLink : TrackLink = mCurrentTrack!!.mHeadTrack000
            if(trackLink.mTrack != null) return trackLink
        }
        if(Game.sTravelMode == TravelMode.MODE888) {
            val trackLink : TrackLink = mCurrentTrack!!.mHeadTrack888
            if(trackLink.mTrack != null) return trackLink
        }
        return mCurrentTrack!!.mHeadTrack333
    }
    override fun getNextTailTrackLink() : TrackLink {
        if (Game.sTravelMode == TravelMode.MODE000) {
            val trackLink: TrackLink = mCurrentTrack!!.mTailTrack000
            if (trackLink.mTrack != null) return trackLink
        }
        if (Game.sTravelMode == TravelMode.MODE888) {
            val trackLink: TrackLink = mCurrentTrack!!.mTailTrack888
            if (trackLink.mTrack != null) return trackLink
        }
        return mCurrentTrack!!.mTailTrack333
    }
    override fun update() {
        super.update()
        mAnimateStep++
        if(mAnimateStep > mAnimateStepSize) mAnimateStep = 0
        if(mAnimateStep == 0){
            mAnimateStage++
            if(mAnimateStage > 13) mAnimateStage = 0
        }
    }
    override fun factoredVelocity() : Float {
        return mVelocity * MainActivity.sGameSpeed * GameFragment.sScale * GameViewModel.sSpeedRamp
    }
    override fun draw(canvas: Canvas, pos : Vec2D, alpha : Int){
        if(alpha == 255) {

            var bm = MainActivity.sBlueLightOn

            when (mAnimateStage) {
                0  -> bm = MainActivity.sRunner030
                1  -> bm = MainActivity.sRunner060
                2  -> bm = MainActivity.sRunner090
                3  -> bm = MainActivity.sRunner090
                4  -> bm = MainActivity.sRunner120
                5  -> bm = MainActivity.sRunner150
                6  -> bm = MainActivity.sRunner180
                7  -> bm = MainActivity.sRunner030b
                8  -> bm = MainActivity.sRunner060b
                9  -> bm = MainActivity.sRunner090b
                10 -> bm = MainActivity.sRunner090b
                11 -> bm = MainActivity.sRunner120b
                12 -> bm = MainActivity.sRunner150b
                13 -> bm = MainActivity.sRunner180b
            }
            if(mJumping) bm = MainActivity.sRunner090j
/*
            if((mAnimateStage == 6) or (mAnimateStage == 13)){
                if(GameViewModel.sGameState == GameState.PLAYING) {
                    if(mJumping == false) {
                        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
                        MainActivity.sSoundPool.play(MainActivity.sSoundBump, vol, vol, 0, 0, 1F)
                    }
                }
            }
*/
            val scale = 1.0F
            val mat01 = Matrix()
            mat01.preScale(mRadius * 2F * scale / bm.width, mRadius * 2F * scale / bm.width)
            mat01.postTranslate(-mRadius * scale, -mRadius * scale * bm.height / bm.width)
            mat01.postRotate(pos.ang)
            mat01.postTranslate(pos.x, pos.y)
            mBitmapPaint.alpha = alpha
            canvas.drawBitmap(bm, mat01, mBitmapPaint)

            /*

            mLinePaint.color = Color.BLUE

            val radOuter = mRadius - mLineThickness / 2F
            drawShineSpot(canvas, 200)
            canvas.drawCircle(
                pos.x,
                pos.y,
                radOuter,
                mLinePaint
            )

             */
        }
//        else {
//            mAlphaPaint.style = Paint.Style.FILL
//            mAlphaPaint.color = Color.argb(alpha, 0, 255, 255)
//            canvas.drawCircle(pos.x, pos.y, mRadius, mAlphaPaint)
//        }
    }
}