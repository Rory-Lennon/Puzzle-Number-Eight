package net.blackseedapps.puzzle8.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import net.blackseedapps.puzzle8.GameObject
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.random.Random

class ScoreObject() : GameObject() {
    private val LOG_TAG = "ScoreObject "

    var mScoreVal = 0
    var mHoopPaint = Paint()
    var mInnerPaint = Paint()
    var mHoopThickness = 0F

    init{
        mVelocity = 1.0F
    }
    fun collision(velocity : Float) {
        mCollisionBlip = (mRadius * 4F / mVelocity).toInt()
    }
    override fun getNextHeadTrackLink() : TrackLink {
        if(mCurrentTrack!!.mHeadTrack888.mTrack != null){
            val rndm = Random.nextInt(2)
            if(rndm == 0) return mCurrentTrack!!.mHeadTrack888
        }
        if(mCurrentTrack!!.mHeadTrack000.mTrack != null){
            val rndm = Random.nextInt(2)
            if(rndm == 0) return mCurrentTrack!!.mHeadTrack000
        }
        return mCurrentTrack!!.mHeadTrack333
    }
    override fun getNextTailTrackLink() : TrackLink {
        if(mCurrentTrack!!.mTailTrack888.mTrack != null){
            val rndm = Random.nextInt(2)
            if(rndm == 0) return mCurrentTrack!!.mTailTrack888
        }
        if(mCurrentTrack!!.mTailTrack000.mTrack != null){
            val rndm = Random.nextInt(2)
            if(rndm == 0) return mCurrentTrack!!.mTailTrack000
        }
        return mCurrentTrack!!.mTailTrack333
    }
    override fun surfaceChanged() {
        super.surfaceChanged()
        mHoopPaint.style = Paint.Style.STROKE
        mHoopThickness = mRadius * 0.458F
        mHoopPaint.strokeWidth = mHoopThickness
        mLinePaint.strokeWidth = mLineThickness
        mInnerPaint.style = Paint.Style.FILL
        mInnerPaint.color = Color.WHITE
    }
    override fun draw(canvas: Canvas, pos : Vec2D, alpha : Int){
        var bm = MainActivity.sGradientGreen
        if(mScoreVal == 1)  {
            bm = MainActivity.sGradientGreen
            mLinePaint.color = Color.GREEN
        }
        if(mScoreVal == -1)  {
            bm = MainActivity.sGradientRed
            mLinePaint.color = Color.RED
        }
        val scale = 1F
        val mat01 = Matrix()
        mat01.preScale(mRadius * 2F * scale / bm.width, mRadius * 2F * scale / bm.width)
        mat01.postTranslate(-mRadius * scale, -mRadius * scale)
        mat01.postTranslate(pos.x, pos.y)
        mBitmapPaint.alpha = alpha
        canvas.drawBitmap(bm, mat01, mBitmapPaint)
        if(alpha == 255) drawShineSpot(canvas, 200)
        canvas.drawCircle(pos.x, pos.y, mRadius + mLinePaint.strokeWidth / 2F, mLinePaint)

//        if(mCollisionBlip > 0) {
//            canvas.drawCircle(pos.x, pos.y, mCollisionBlip.toFloat(), mLinePaint)
//        }
    }
    override fun factoredVelocity() : Float {
        return mVelocity * MainActivity.sGameSpeed * GameFragment.sScale
    }
}