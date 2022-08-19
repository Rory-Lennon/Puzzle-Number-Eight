package net.blackseedapps.puzzle8.game

import android.graphics.Color
import android.util.Log
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import kotlin.math.sin

class Game02 : Game() {
    private val LOG_TAG = "Game02 "

    val mArc01 = TrackArc()
    val mArc02 = TrackArc()
    val mArc03 = TrackArc()
    val mArc04 = TrackArc()
    val mArc05 = TrackArc()
    val mArc06 = TrackArc()
    val mArc07 = TrackArc()
    val mArc08 = TrackArc()
    val mArc09 = TrackArc()
    val mArc10 = TrackArc()
    val mArc11 = TrackArc()
    val mArc12 = TrackArc()
    val mArc13 = TrackArc()
    val mArc14 = TrackArc()
    val mArc15 = TrackArc()
    val mArc16 = TrackArc()

    val mStraight17 = TrackStraight()
    val mStraight18 = TrackStraight()
    val mStraight19 = TrackStraight()
    val mStraight20 = TrackStraight()
    val mStraight21 = TrackStraight()
    val mStraight22 = TrackStraight()
    val mStraight23 = TrackStraight()
    val mStraight24 = TrackStraight()

    init{

        mAllTracks = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mArc13, mArc14, mArc15, mArc16, mStraight17, mStraight18,
            mStraight19, mStraight20, mStraight21, mStraight22, mStraight23, mStraight24)

        mTracks333 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mArc13, mArc14, mArc15, mArc16)

        mTracks000 = arrayOf(mArc01, mArc02, mArc07, mArc08, mStraight21, mStraight22,
            mStraight23, mStraight24)

        mTracks888 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mStraight17, mStraight18, mStraight19, mStraight20)

        mTracksMiddleX = arrayOf(mArc03, mArc04, mArc05, mArc06,
            mArc09, mArc10, mArc11, mArc12, mArc13, mArc14, mArc15, mArc16, mStraight17, mStraight18,
            mStraight19, mStraight20, mStraight21, mStraight22, mStraight23, mStraight24)

//        defineTrack()
        initialiseGame()
    }
    override fun initialiseGame() {
        mMaxScore = 40
        super.initialiseGame()
        mPlayerObject.mCurrentTrack = mArc02
        mScoreObject01.mCurrentTrack = mArc01
        mScoreObject01.mScoreVal = -1
        mScoreObject02.mCurrentTrack = mArc08
        mScoreObject02.mScoreVal = 1
    }
    override fun checkHighScore(){
        if(mScore > MainActivity.sHighScore02) MainActivity.sHighScore02 = mScore
    }
    override fun getHighScore() : Int {
        return MainActivity.sHighScore02
    }
    override fun surfaceChanged(){

        Log.i(LOG_TAG, "Game02 ")

        mThickness = mRadius * (1F - mRadFac02)
        mBotPos.x = mTopPos.x
        val angDegs = -45F
        val angRads = -2F * Math.PI * angDegs / 360F
        val yLen = mRadius * sin(angRads).toFloat()
        mBotPos.y = mTopPos.y + yLen * mRadFac02 * 4F
        defineTrack()
        super.surfaceChanged()
    }
    fun defineTrack(){
        mArc01.mRefPoint.x = mTopPos.x
        mArc01.mRefPoint.y = mTopPos.y
        mArc01.mRad01 = mRadius
        mArc01.mRad02 = mRadius * mRadFac02
        mArc01.mStartAng = 180F
        mArc01.mSweepAng = 180F
        mArc01.mNum = 1
        mArc01.mHeadTrack333.mTrack = mArc03
        mArc01.mTailTrack333.mTrack = mArc05
        mArc01.mHeadTrack000.mTrack = mStraight23
        mArc01.mTailTrack000.mTrack = mStraight21

        mArc02.mRefPoint.x = mTopPos.x
        mArc02.mRefPoint.y = mTopPos.y
        mArc02.mRad01 = mRadius * mRadFac02
        mArc02.mRad02 = mRadius * mRadFac03
        mArc02.mStartAng = 180F
        mArc02.mSweepAng = 180F
        mArc02.mNum = 2
        mArc02.mHeadTrack333.mTrack = mArc04
        mArc02.mTailTrack333.mTrack = mArc06
        mArc02.mHeadTrack000.mTrack = mStraight24
        mArc02.mTailTrack000.mTrack = mStraight22

        mArc03.mRefPoint.x = mTopPos.x
        mArc03.mRefPoint.y = mTopPos.y
        mArc03.mRad01 = mRadius
        mArc03.mRad02 = mRadius * mRadFac02
        mArc03.mStartAng = 0F
        mArc03.mSweepAng = 45F
        mArc03.mNum = 3
        mArc03.mHeadTrack333.mTrack = mArc13
        mArc03.mTailTrack333.mTrack = mArc01
        mArc03.mHeadTrack888.mTrack = mStraight20
        mArc03.mHeadTrack888.mTwisted = true

        mArc04.mRefPoint.x = mTopPos.x
        mArc04.mRefPoint.y = mTopPos.y
        mArc04.mRad01 = mRadius * mRadFac02
        mArc04.mRad02 = mRadius * mRadFac03
        mArc04.mStartAng = 0F
        mArc04.mSweepAng = 45F
        mArc04.mNum = 4
        mArc04.mHeadTrack333.mTrack = mArc14
        mArc04.mTailTrack333.mTrack = mArc02
        mArc04.mHeadTrack888.mTrack = mStraight19
        mArc04.mHeadTrack888.mTwisted = true

        mArc05.mRefPoint.x = mTopPos.x
        mArc05.mRefPoint.y = mTopPos.y
        mArc05.mRad01 = mRadius
        mArc05.mRad02 = mRadius * mRadFac02
        mArc05.mStartAng = 135F
        mArc05.mSweepAng = 45F
        mArc05.mNum = 5
        mArc05.mHeadTrack333.mTrack = mArc01
        mArc05.mTailTrack333.mTrack = mArc13
        mArc05.mTailTrack888.mTrack = mStraight18

        mArc06.mRefPoint.x = mTopPos.x
        mArc06.mRefPoint.y = mTopPos.y
        mArc06.mRad01 = mRadius * mRadFac02
        mArc06.mRad02 = mRadius * mRadFac03
        mArc06.mStartAng = 135F
        mArc06.mSweepAng = 45F
        mArc06.mNum = 6
        mArc06.mHeadTrack333.mTrack = mArc02
        mArc06.mTailTrack333.mTrack = mArc14
        mArc06.mTailTrack888.mTrack = mStraight17

        mArc07.mRefPoint.x = mBotPos.x
        mArc07.mRefPoint.y = mBotPos.y
        mArc07.mRad01 = mRadius
        mArc07.mRad02 = mRadius * mRadFac02
        mArc07.mStartAng = 0F
        mArc07.mSweepAng = 180F
        mArc07.mNum = 7
        mArc07.mHeadTrack333.mTrack = mArc09
        mArc07.mTailTrack333.mTrack = mArc11
        mArc07.mHeadTrack000.mTrack = mStraight21
        mArc07.mTailTrack000.mTrack = mStraight23

        mArc08.mRefPoint.x = mBotPos.x
        mArc08.mRefPoint.y = mBotPos.y
        mArc08.mRad01 = mRadius * mRadFac02
        mArc08.mRad02 = mRadius * mRadFac03
        mArc08.mStartAng = 0F
        mArc08.mSweepAng = 180F
        mArc08.mNum = 8
        mArc08.mHeadTrack333.mTrack = mArc10
        mArc08.mTailTrack333.mTrack = mArc12
        mArc08.mHeadTrack000.mTrack = mStraight22
        mArc08.mTailTrack000.mTrack = mStraight24

        mArc09.mRefPoint.x = mBotPos.x
        mArc09.mRefPoint.y = mBotPos.y
        mArc09.mRad01 = mRadius
        mArc09.mRad02 = mRadius * mRadFac02
        mArc09.mStartAng = 180F
        mArc09.mSweepAng = 45F
        mArc09.mNum = 9
        mArc09.mHeadTrack333.mTrack = mArc15
        mArc09.mTailTrack333.mTrack = mArc07
        mArc09.mHeadTrack888.mTrack = mStraight19

        mArc10.mRefPoint.x = mBotPos.x
        mArc10.mRefPoint.y = mBotPos.y
        mArc10.mRad01 = mRadius * mRadFac02
        mArc10.mRad02 = mRadius * mRadFac03
        mArc10.mStartAng = 180F
        mArc10.mSweepAng = 45F
        mArc10.mNum = 10
        mArc10.mHeadTrack333.mTrack = mArc16
        mArc10.mTailTrack333.mTrack = mArc08
        mArc10.mHeadTrack888.mTrack = mStraight20

        mArc11.mRefPoint.x = mBotPos.x
        mArc11.mRefPoint.y = mBotPos.y
        mArc11.mRad01 = mRadius
        mArc11.mRad02 = mRadius * mRadFac02
        mArc11.mStartAng = 315F
        mArc11.mSweepAng = 45F
        mArc11.mNum = 11
        mArc11.mHeadTrack333.mTrack = mArc07
        mArc11.mTailTrack333.mTrack = mArc15
        mArc11.mTailTrack888.mTrack = mStraight17
        mArc11.mTailTrack888.mTwisted = true

        mArc12.mRefPoint.x = mBotPos.x
        mArc12.mRefPoint.y = mBotPos.y
        mArc12.mRad01 = mRadius * mRadFac02
        mArc12.mRad02 = mRadius * mRadFac03
        mArc12.mStartAng = 315F
        mArc12.mSweepAng = 45F
        mArc12.mNum = 12
        mArc12.mHeadTrack333.mTrack = mArc08
        mArc12.mTailTrack333.mTrack = mArc16
        mArc12.mTailTrack888.mTrack = mStraight18
        mArc12.mTailTrack888.mTwisted = true

        mArc13.mRefPoint.x = mTopPos.x
        mArc13.mRefPoint.y = mTopPos.y
        mArc13.mRad01 = mRadius
        mArc13.mRad02 = mRadius * mRadFac02
        mArc13.mStartAng = 45F
        mArc13.mSweepAng = 90F
        mArc13.mNum = 13
        mArc13.mHeadTrack333.mTrack = mArc05
        mArc13.mTailTrack333.mTrack = mArc03

        mArc14.mRefPoint.x = mTopPos.x
        mArc14.mRefPoint.y = mTopPos.y
        mArc14.mRad01 = mRadius * mRadFac02
        mArc14.mRad02 = mRadius * mRadFac03
        mArc14.mStartAng = 45F
        mArc14.mSweepAng = 90F
        mArc14.mNum = 14
        mArc14.mHeadTrack333.mTrack = mArc06
        mArc14.mTailTrack333.mTrack = mArc04

        mArc15.mRefPoint.x = mBotPos.x
        mArc15.mRefPoint.y = mBotPos.y
        mArc15.mRad01 = mRadius
        mArc15.mRad02 = mRadius * mRadFac02
        mArc15.mStartAng = 225F
        mArc15.mSweepAng = 90F
        mArc15.mNum = 15
        mArc15.mHeadTrack333.mTrack = mArc11
        mArc15.mTailTrack333.mTrack = mArc09

        mArc16.mRefPoint.x = mBotPos.x
        mArc16.mRefPoint.y = mBotPos.y
        mArc16.mRad01 = mRadius * mRadFac02
        mArc16.mRad02 = mRadius * mRadFac03
        mArc16.mStartAng = 225F
        mArc16.mSweepAng = 90F
        mArc16.mNum = 16
        mArc16.mHeadTrack333.mTrack = mArc12
        mArc16.mTailTrack333.mTrack = mArc10

        mStraight17.mNum = 17
        mStraight17.mTwisted = true
        mStraight17.mHeadTrack333.mTrack = mArc06
        mStraight17.mTailTrack333.mTrack = mArc11
        mStraight17.mTailTrack333.mTwisted = true
        mStraight17.mHeadPoint = mArc06
        mStraight17.mTailPoint = mArc11

        mStraight18.mNum = 18
        mStraight18.mTwisted = true
        mStraight18.mHeadTrack333.mTrack = mArc05
        mStraight18.mTailTrack333.mTrack = mArc12
        mStraight18.mTailTrack333.mTwisted = true
        mStraight18.mHeadPoint = mArc05
        mStraight18.mTailPoint = mArc12

        mStraight19.mNum = 19
        mStraight19.mTwisted = true
        mStraight19.mHeadTrack333.mTrack = mArc04
        mStraight19.mHeadTrack333.mTwisted = true
        mStraight19.mTailTrack333.mTrack = mArc09
        mStraight19.mHeadPoint = mArc14
        mStraight19.mTailPoint = mArc15

        mStraight20.mNum = 20
        mStraight20.mTwisted = true
        mStraight20.mHeadTrack333.mTrack = mArc03
        mStraight20.mHeadTrack333.mTwisted = true
        mStraight20.mTailTrack333.mTrack = mArc10
        mStraight20.mHeadPoint = mArc13
        mStraight20.mTailPoint = mArc16

        mStraight21.mNum = 21
        mStraight21.mHeadTrack333.mTrack = mArc01
        mStraight21.mTailTrack333.mTrack = mArc07
        mStraight21.mHeadPoint = mArc01
        mStraight21.mTailPoint = mArc09

        mStraight22.mNum = 22
        mStraight22.mHeadTrack333.mTrack = mArc02
        mStraight22.mTailTrack333.mTrack = mArc08
        mStraight22.mHeadPoint = mArc02
        mStraight22.mTailPoint = mArc10

        mStraight23.mNum = 23
        mStraight23.mHeadTrack333.mTrack = mArc07
        mStraight23.mTailTrack333.mTrack = mArc01
        mStraight23.mHeadPoint = mArc07
        mStraight23.mTailPoint = mArc03

        mStraight24.mNum = 24
        mStraight24.mHeadTrack333.mTrack = mArc08
        mStraight24.mTailTrack333.mTrack = mArc02
        mStraight24.mHeadPoint = mArc08
        mStraight24.mTailPoint = mArc04
    }
}