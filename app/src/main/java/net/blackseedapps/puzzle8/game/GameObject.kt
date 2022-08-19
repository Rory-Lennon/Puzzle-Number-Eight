package net.blackseedapps.puzzle8

import android.graphics.*
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.game.*
import kotlin.random.Random

open class GameObject() {
    private val LOG_TAG = "GameObject "
    var mGreenColorBlip = 0
    var mRedColorBlip = 0
    var mCollisionBlip = 0
    var mDoubleCollisionBlip = 0
    var mTrailBlip = 0
    var mBitmapPaint = Paint()
    var mLinePaint = Paint()
    var mShineSpotPaint = Paint()
    var mGreenMushroomPaint = Paint()
    var mRedMushroomPaint = Paint()
    val mPaintScoreLocalText = Paint()
    val mPaintScoreLocalFill = Paint()
    var mVelocity = 0F
    var mProgress = 0F
    var mDirectionOfTravel = true
    var mCurrentTrack : Track? = null
    var mRadius = 0F
    var mPosition: Vec2D = Vec2D()
    var mGreenMushroomBlip = 0
    var mRedMushroomBlip = 0
    var mLineThickness = 0F
    var mRedFlashBlip = 0
    var mGreenFlashBlip = 0
    var mMaxTrailLength = 0
    var mTrail00 = TrailNode(1)
    var mTrail01 = TrailNode(2)
    var mTrail02 = TrailNode(3)
    var mTrail03 = TrailNode(4)
    var mTrailArray = arrayOf(mTrail00, mTrail01, mTrail02, mTrail03)
    var mAnimateStage = 0
    var mAnimateStepSize = 0
    var mAnimateStep = 0
    val mGreenClrArray = arrayOf(Color.rgb(200, 255, 200), Color.rgb(0, 225, 0), Color.rgb(0, 150, 0))
    val mRedClrArray = arrayOf(Color.rgb(255, 200, 200), Color.rgb(225, 0, 0), Color.rgb(150, 0, 0))

    fun setMaxTrailLength(length : Int){
        mMaxTrailLength = length
        for (trail in mTrailArray) trail.setAlpha(length)
    }
    open fun surfaceChanged() {
        mShineSpotPaint.style = Paint.Style.FILL
        mShineSpotPaint.color = Color.WHITE
        mGreenMushroomPaint.style = Paint.Style.STROKE
        mGreenMushroomPaint.setStrokeWidth(GameFragment.sHeight / 40F)
        mRedMushroomPaint.style = Paint.Style.STROKE
        mRedMushroomPaint.alpha = 75
        mRedMushroomPaint.setStrokeWidth(GameFragment.sHeight / 40F)
        mPaintScoreLocalText.color = Color.rgb(255, 255, 255)
        mPaintScoreLocalText.typeface = MainActivity.sTypefaceScore
        mPaintScoreLocalText.style = Paint.Style.FILL
        val txh = GameFragment.sHeight / 10F
        mPaintScoreLocalText.setTextSize(txh)
        mPaintScoreLocalFill.style = Paint.Style.FILL
        mPaintScoreLocalFill.color = Color.rgb(255, 255, 255)
        mLinePaint.style = Paint.Style.STROKE
        mLinePaint.color = Color.WHITE
        mLineThickness = mRadius * 0.183F
        mLinePaint.strokeWidth = mLineThickness
    }
    fun collisionDection(otherObject : GameObject) : Boolean {
        val len = mPosition.getGeometricDistance(otherObject.mPosition)
        if(len < ((mRadius + otherObject.mRadius) * 0.9F)) return true
        return false
    }
    open fun draw(canvas: Canvas){
        if(mTrail03.mTrailMode == TrailMode.ALIVE) draw(canvas, mTrail03.mPosition, mTrail03.mAlpha)
        if(mTrail02.mTrailMode == TrailMode.ALIVE) draw(canvas, mTrail02.mPosition, mTrail02.mAlpha)
        if(mTrail01.mTrailMode == TrailMode.ALIVE) draw(canvas, mTrail01.mPosition, mTrail01.mAlpha)
        if(mTrail00.mTrailMode == TrailMode.ALIVE) draw(canvas, mTrail00.mPosition, mTrail00.mAlpha)
        draw(canvas, mPosition, 255)
        redFlash(canvas)
        greenFlash(canvas)
        redMushroom(canvas)
        greenMushroom(canvas)
    }
    open fun draw(canvas: Canvas, pos : Vec2D, alpha : Int){}

    open fun update() {
        //////////////////
        mGreenMushroomBlip -= 4
        if(mGreenMushroomBlip < 0) mGreenMushroomBlip = 0
        //////////////////
        mRedMushroomBlip -= 4
        if(mRedMushroomBlip < 0) mRedMushroomBlip = 0
        //////////////////
        mRedFlashBlip -= 20
        if(mRedFlashBlip < 0) mRedFlashBlip = 0
        /////////////
        mGreenFlashBlip -= 20
        if(mGreenFlashBlip < 0) mGreenFlashBlip = 0
        /////////////
        mCollisionBlip--
        if(mCollisionBlip < 0) mCollisionBlip = 0
        /////////////////
        mDoubleCollisionBlip--
        if(mDoubleCollisionBlip < 0) mDoubleCollisionBlip = 0
        /////////////////

        mTrailBlip--
        if(mTrailBlip < 0) mTrailBlip = 5
        if(mTrailBlip == 0) {

            if (mMaxTrailLength > 3) {
                mTrail03.mTrailMode = TrailMode.ALIVE
                mTrail03.mPosition.x = mTrail02.mPosition.x
                mTrail03.mPosition.y = mTrail02.mPosition.y
            }
            if (mMaxTrailLength > 2) {
                mTrail02.mTrailMode = TrailMode.ALIVE
                mTrail02.mPosition.x = mTrail01.mPosition.x
                mTrail02.mPosition.y = mTrail01.mPosition.y
            }
            if (mMaxTrailLength > 1) {
                mTrail01.mTrailMode = TrailMode.ALIVE
                mTrail01.mPosition.x = mTrail00.mPosition.x
                mTrail01.mPosition.y = mTrail00.mPosition.y
            }
            if (mMaxTrailLength > 0) {
                mTrail00.mTrailMode = TrailMode.ALIVE
                mTrail00.mPosition.x = mPosition.x
                mTrail00.mPosition.y = mPosition.y
            }
        }
        checkProgress()
    }
    fun checkProgress() {
        if(mCurrentTrack == null) return
        mProgress += factoredVelocity()
        val len = mCurrentTrack!!.mTrackLength
        if(mProgress > len) {
            mProgress = mProgress - len
            val trackLink: TrackLink
            if(mDirectionOfTravel == true) {
                trackLink = getNextHeadTrackLink()
                mCurrentTrack = trackLink.mTrack
            }
            else {
                trackLink = getNextTailTrackLink()
                mCurrentTrack = trackLink.mTrack
            }
            if(trackLink.mTwisted) changeDirection()
        }
        updateLoc()
    }
    open fun factoredVelocity() : Float {
        return 0F
    }
    fun updateLoc(){
        if(mCurrentTrack == null) return
        mPosition = mCurrentTrack!!.getLoc(mProgress, mDirectionOfTravel)!!
    }
    fun changeDirection() {
        mDirectionOfTravel = !mDirectionOfTravel
    }
    fun changeDirPos() {
        changeDirection()
        val len = mCurrentTrack!!.mTrackLength
        mProgress = len - mProgress
    }
    open fun getNextHeadTrackLink() : TrackLink {
        return mCurrentTrack!!.mHeadTrack333
    }
    open fun getNextTailTrackLink() : TrackLink {
        return mCurrentTrack!!.mTailTrack333
    }
    open fun drawShineSpot(canvas: Canvas, alpha : Int){
        val shinePos = Vec2D(mPosition.x, mPosition.y)
        shinePos.x += mRadius / 2.25F
        shinePos.y -= mRadius / 2.25F
        mShineSpotPaint.alpha = alpha
        canvas.drawCircle(shinePos.x, shinePos.y, mRadius / 3.0F, mShineSpotPaint)
    }
    fun redFlash(canvas: Canvas) {
        if(mRedFlashBlip <= 0) return
        val flashInnerRadius = (200F).toInt()
        val flashRad = flashInnerRadius + mRedFlashBlip
        val bm = MainActivity.sFlashBitmapRed
        val mat = Matrix()
        mat.preScale(flashRad.toFloat() / bm.width, flashRad.toFloat() / bm.height)
        mat.postTranslate(-flashRad.toFloat() / 2F, -flashRad.toFloat() / 2F)
        mat.postTranslate(mPosition.x, mPosition.y)
        canvas.drawBitmap(bm, mat, null)
    }
    fun greenFlash(canvas: Canvas) {
        if(mGreenFlashBlip <= 0) return
        val flashInnerRadius = (200F).toInt()
        val flashRad = flashInnerRadius + mGreenFlashBlip
        val bm = MainActivity.sFlashBitmapGreen
        val mat = Matrix()
        mat.preScale(flashRad.toFloat() / bm.width, flashRad.toFloat() / bm.height)
        mat.postTranslate(-flashRad.toFloat() / 2F, -flashRad.toFloat() / 2F)
        mat.postTranslate(mPosition.x, mPosition.y)
        canvas.drawBitmap(bm, mat, null)
    }
    fun greenMushroom(canvas: Canvas) {
        if(mGreenMushroomBlip <= 0) return
        mGreenColorBlip++
        if(mGreenColorBlip > 10) {
            mGreenColorBlip = 0
            val rv = Random.nextInt(3)
            mGreenMushroomPaint.color = mGreenClrArray[rv]
        }
        val mushroomOuterRadius = (GameFragment.sHeight / 20F).toInt()
        val circleRadius = mushroomOuterRadius - mGreenMushroomBlip
        mGreenMushroomPaint.alpha = 150
        canvas.drawCircle(mPosition.x, mPosition.y, circleRadius.toFloat(), mGreenMushroomPaint)
        drawScoreLocal(canvas, 1)
    }
    fun redMushroom(canvas: Canvas) {
        if(mRedMushroomBlip <= 0) return
        mRedColorBlip++
        if(mRedColorBlip > 10) {
            mRedColorBlip = 0
            val rv = Random.nextInt(3)
            mRedMushroomPaint.color = mRedClrArray[rv]
        }
        val mushroomOuterRadius = (GameFragment.sHeight / 20F).toInt()
        val circleRadius = mushroomOuterRadius - mRedMushroomBlip
        mRedMushroomPaint.alpha = 150
        canvas.drawCircle(mPosition.x, mPosition.y, circleRadius.toFloat(), mRedMushroomPaint)
        drawScoreLocal(canvas, -1)
    }
    fun drawScoreLocal(canvas: Canvas, scoreVal : Int){
        if(scoreVal == 1) mPaintScoreLocalText.color = Color.rgb(0, 200, 0)
        else mPaintScoreLocalText.color = Color.rgb(200, 0, 0)
        val rectTextBounds = Rect()
        var strScoreInc : String = scoreVal.toString()
        if(scoreVal > 0) strScoreInc = "+" + strScoreInc
        mPaintScoreLocalText.getTextBounds(strScoreInc, 0, strScoreInc.length, rectTextBounds)
        var sx = mPosition.x + rectTextBounds.height() / 2F
        var sy = mPosition.y + rectTextBounds.height() / 2F
        var cx = mPosition.x + rectTextBounds.width()
        var cy = mPosition.y
        if(sx < 0 ) sx += rectTextBounds.width()
        if(sx > GameFragment.sWidth - rectTextBounds.width()) sx -= rectTextBounds.width()
        mPaintScoreLocalFill.alpha = 150
        canvas.drawCircle(cx, cy, rectTextBounds.height().toFloat(), mPaintScoreLocalFill)
        canvas.drawText(strScoreInc, sx, sy, mPaintScoreLocalText)
    }
}
