package net.blackseedapps.puzzle8.game

import android.graphics.*
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

open class Track() {
    private val LOG_TAG = "Track "

    init{}

    val mPaintCircle = Paint()
    val mPaintText = Paint()
    var mColor = Color.GRAY
    var mNum = 0
    var mCentroid = Vec2D()
    var mLightbulbLinePaint = Paint()
    val mRefPoint = Vec2D()
    var mCircleRad = 0F
    var mTrackLength = 0F

    var mTwisted = false

    val mStartPoint = Vec2D()
    val mP01 = Vec2D()
    val mP02 = Vec2D()

    val mHeadTrack333 = TrackLink()
    val mTailTrack333 = TrackLink()
    val mHeadTrack000 = TrackLink()
    val mTailTrack000 = TrackLink()
    val mHeadTrack888 = TrackLink()
    val mTailTrack888 = TrackLink()

    open fun getLoc(progress : Float, dir : Boolean) : Vec2D? { return null }

    open fun drawTrack(canvas: Canvas) {}
    open fun drawTrackBack(canvas: Canvas) {}

    open fun surfaceChanged() {
        mCircleRad = GameFragment.sHeight / 75F
        mPaintCircle.style = Paint.Style.FILL
        mPaintCircle.color = mColor
        mPaintText.color = Color.rgb(0, 255, 0)
        mPaintText.typeface = MainActivity.sTypefaceScore
        mPaintText.style = Paint.Style.FILL
        val txh = GameFragment.sHeight / 60F
        mPaintText.setTextSize(txh)
        mLightbulbLinePaint.style = Paint.Style.STROKE
        mLightbulbLinePaint.color = Color.WHITE
        mLightbulbLinePaint.strokeWidth = GameFragment.sHeight / 500F
    }
    fun drawNumberAtCentroid(canvas: Canvas){
        canvas.drawCircle(mStartPoint.x, mStartPoint.y, mCircleRad / 4F, mPaintCircle)
        canvas.drawCircle(mCentroid.x, mCentroid.y, mCircleRad, mPaintCircle)
        val rectTextBounds = Rect()
        var strText : String = mNum.toString()
        mPaintText.getTextBounds(strText, 0, strText.length, rectTextBounds)
        var sx = mCentroid.x - rectTextBounds.width() / 2F
        var sy = mCentroid.y + rectTextBounds.height() / 2F
        canvas.drawText(strText, sx, sy, mPaintText)
    }
}