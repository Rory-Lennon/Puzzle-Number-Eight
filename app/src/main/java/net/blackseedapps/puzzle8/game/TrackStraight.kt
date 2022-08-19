package net.blackseedapps.puzzle8.game

import android.graphics.Canvas
import android.util.Log
import java.lang.Math.*
import kotlin.math.atan

open class TrackStraight() : Track() {
    private val LOG_TAG = "TrackStraight "

    var mHeadPoint : Track? = null
    var mTailPoint : Track? = null
    var mAngDegs = 0F

    override fun getLoc(progress : Float, dir : Boolean) : Vec2D? {
        val loc = Vec2D()
        val dx = (mTailPoint!!.mStartPoint.x - mHeadPoint!!.mStartPoint.x) * (progress / mTrackLength)
        val dy = (mTailPoint!!.mStartPoint.y - mHeadPoint!!.mStartPoint.y) * (progress / mTrackLength)

        if(dir) {
            loc.x = mTailPoint!!.mStartPoint.x - dx
            loc.y = mTailPoint!!.mStartPoint.y - dy
            loc.ang = mAngDegs + 90F
        }
        else {
            loc.x = mHeadPoint!!.mStartPoint.x + dx
            loc.y = mHeadPoint!!.mStartPoint.y + dy
            loc.ang = mAngDegs - 90F
        }
        return loc
    }
    override fun drawTrack(canvas: Canvas) {
        if(mTwisted == false) {
            canvas.drawLine(
                mHeadPoint!!.mP01.x,
                mHeadPoint!!.mP01.y,
                mTailPoint!!.mP01.x,
                mTailPoint!!.mP01.y,
                Game.sPaint)

            canvas.drawLine(
                mHeadPoint!!.mP02.x,
                mHeadPoint!!.mP02.y,
                mTailPoint!!.mP02.x,
                mTailPoint!!.mP02.y,
                Game.sPaint)
        }
        else
        {
            canvas.drawLine(
                mHeadPoint!!.mP01.x,
                mHeadPoint!!.mP01.y,
                mTailPoint!!.mP02.x,
                mTailPoint!!.mP02.y,
                Game.sPaint)

            canvas.drawLine(
                mHeadPoint!!.mP02.x,
                mHeadPoint!!.mP02.y,
                mTailPoint!!.mP01.x,
                mTailPoint!!.mP01.y,
                Game.sPaint)
        }
    }
    override fun drawTrackBack(canvas: Canvas) {
        canvas.drawLine(
            mHeadPoint!!.mStartPoint.x,
            mHeadPoint!!.mStartPoint.y,
            mTailPoint!!.mStartPoint.x,
            mTailPoint!!.mStartPoint.y,
            Game.sPaint)
    }
    override fun surfaceChanged() {
        super.surfaceChanged()
        val dx = (mHeadPoint!!.mStartPoint.x - mTailPoint!!.mStartPoint.x)
        val dy = (mHeadPoint!!.mStartPoint.y - mTailPoint!!.mStartPoint.y)
        mCentroid.x = mHeadPoint!!.mStartPoint.x
        mCentroid.y = mHeadPoint!!.mStartPoint.y
        val dxy = pow(dx.toDouble(), 2.0) + pow(dy.toDouble(), 2.0)
        mTrackLength = sqrt(dxy).toFloat()
        val angRads = atan2(dy.toDouble(), dx.toDouble()).toFloat()
        mAngDegs = angRads * 360.0F / (2.0F * kotlin.math.PI.toFloat())
    }
}