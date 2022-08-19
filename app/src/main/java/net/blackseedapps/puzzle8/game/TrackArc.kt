package net.blackseedapps.puzzle8.game

import android.graphics.Canvas
import android.graphics.RectF
import kotlin.math.cos
import kotlin.math.sin

class TrackArc() : Track() {
    private val LOG_TAG = "TrackArc "

    var mStartAng = 0F
    var mSweepAng = 0F
    var mRad01 = 0F
    var mRad02 = 0F

    override fun getLoc(progress : Float, dir : Boolean) : Vec2D? {
        val loc = Vec2D()
        val radAve = (mRad01 + mRad02) / 2F
        var angIn = 0F
        if(dir) {
            angIn = mStartAng + 90F + (mSweepAng * progress / mTrackLength)
            loc.ang = angIn + 90F
        }
        else {
            angIn = mStartAng + 90F + mSweepAng - (mSweepAng * progress / mTrackLength)
            loc.ang = angIn + 270F
        }
        val angInRads = 2F * Math.PI.toFloat() * angIn / 360F
        loc.x = radAve * sin(angInRads) + mRefPoint.x
        loc.y = -radAve * cos(angInRads) + mRefPoint.y
        return loc
    }
    override fun drawTrack(canvas: Canvas) {
        drawArc(canvas, mRad01)
        drawArc(canvas, mRad02)
    }
    fun drawArc(canvas: Canvas, rad : Float) {
        val arcRect = RectF()
        arcRect.left = mRefPoint.x - rad
        arcRect.right = mRefPoint.x + rad
        arcRect.top = mRefPoint.y - rad
        arcRect.bottom = mRefPoint.y + rad
        canvas.drawArc(arcRect, mStartAng, mSweepAng, false, Game.sPaint)
    }
    override fun drawTrackBack(canvas: Canvas) {
        val radAve = (mRad01 + mRad02) / 2F
        drawArc(canvas, radAve)
//        drawNumberAtCentroid(canvas)
    }
    override fun surfaceChanged() {
        super.surfaceChanged()
        val radAve = (mRad01 + mRad02) / 2F
        val angIn = mStartAng + 90F
        val angInRads = 2F * Math.PI.toFloat() * angIn / 360F
        mStartPoint.x = radAve * sin(angInRads) + mRefPoint.x
        mStartPoint.y = -radAve * cos(angInRads) + mRefPoint.y
        mP01.x = mRad01 * sin(angInRads) + mRefPoint.x
        mP01.y = -mRad01 * cos(angInRads) + mRefPoint.y
        mP02.x = mRad02 * sin(angInRads) + mRefPoint.x
        mP02.y = -mRad02 * cos(angInRads) + mRefPoint.y
        val angAve = mStartAng + 90F + mSweepAng / 2F
        val angAveRads = 2F * Math.PI.toFloat() * angAve / 360F
        mCentroid.x = radAve * sin(angAveRads).toFloat() + mRefPoint.x
        mCentroid.y = -radAve * cos(angAveRads).toFloat() + mRefPoint.y
        mTrackLength = radAve * 2F * Math.PI.toFloat() * mSweepAng / 360F
    }
}