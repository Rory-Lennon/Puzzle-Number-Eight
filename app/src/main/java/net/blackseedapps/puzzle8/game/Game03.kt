package net.blackseedapps.puzzle8.game

import android.util.Log
import net.blackseedapps.puzzle8.MainActivity
import kotlin.math.sin

class Game03 : Game() {
    private val LOG_TAG = "Game03 "

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

    val mArc31 = TrackArc()
    val mArc32 = TrackArc()
    val mArc33 = TrackArc()
    val mArc34 = TrackArc()
    val mArc41 = TrackArc()
    val mArc42 = TrackArc()
    val mArc43 = TrackArc()
    val mArc44 = TrackArc()

    val mStraight51 = TrackStraight()
    val mStraight52 = TrackStraight()
    val mStraight61 = TrackStraight()
    val mStraight62 = TrackStraight()

    val mStraight71 = TrackStraight()
    val mStraight72 = TrackStraight()
    val mStraight73 = TrackStraight()
    val mStraight74 = TrackStraight()
    val mStraight75 = TrackStraight()
    val mStraight76 = TrackStraight()
    val mStraight77 = TrackStraight()
    val mStraight78 = TrackStraight()

    init{

        mAllTracks = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mArc13, mArc14, mArc15, mArc16, mArc31,
            mArc32, mArc33, mArc34, mArc41, mArc42, mArc43, mArc44,
            mStraight51, mStraight52, mStraight61, mStraight62, mStraight71, mStraight72,
            mStraight73, mStraight74, mStraight75, mStraight76, mStraight77, mStraight78)

        mTracks333 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mArc13, mArc14, mArc15, mArc16, mArc31,
            mArc32, mArc33, mArc34, mArc41, mArc42, mArc43, mArc44)

        mTracks000 = arrayOf(mArc01, mArc02, mArc07, mArc08, mArc31, mArc41, mStraight51,
            mStraight52, mStraight75, mStraight76, mStraight77, mStraight78)

        mTracks888 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mArc31, mArc32, mArc34, mArc41, mArc42, mArc44,
            mStraight61, mStraight62, mStraight71, mStraight72, mStraight73, mStraight74)

        mTracksMiddleX = mAllTracks

        initialiseGame()
    }
    override fun initialiseGame() {
        mMaxScore = 60
        super.initialiseGame()
        mPlayerObject.mCurrentTrack = mArc02
        mScoreObject01.mCurrentTrack = mArc01
        mScoreObject01.mScoreVal = -1
        mScoreObject02.mCurrentTrack = mArc31
        mScoreObject02.mScoreVal = 1
    }
    override fun checkHighScore(){
        if(mScore > MainActivity.sHighScore03) MainActivity.sHighScore03 = mScore
    }
    override fun getHighScore() : Int {
        return MainActivity.sHighScore03
    }
    override fun surfaceChanged(){

        Log.i(LOG_TAG, "Game03 ")

        mThickness = mRadius * (1F - mRadFac02)
        mBotPos.x = mTopPos.x
        val angDegs = -45F
        val angRads = -2F * Math.PI * angDegs / 360F
        val rootLen = mRadius * sin(angRads).toFloat()
        mBotPos.y = mTopPos.y + rootLen * mRadFac02 * 4F
/*
        val radFac03p5 = (mRadFac03 + mRadFac04) / 2F
        val radFac02p5 = (mRadFac02 + mRadFac03) / 2F
        val radFac01p5 = (1F + mRadFac02) / 2F
        val L = rootLen * mRadFac02 * 2F
        val R = mRadius * mRadFac03
        val smallAngRads = acos(R / L)
        val smallAngDegs = smallAngRads * 360F / (2F * Math.PI)
        val remainingAngDegs = 90F - smallAngDegs
        Log.d(LOG_TAG, "smallAngDegs ****************************************** $smallAngDegs $remainingAngDegs")
 */
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
        mArc01.mHeadTrack000.mTrack = mStraight77
        mArc01.mTailTrack000.mTrack = mStraight75

        mArc02.mRefPoint.x = mTopPos.x
        mArc02.mRefPoint.y = mTopPos.y
        mArc02.mRad01 = mRadius * mRadFac02
        mArc02.mRad02 = mRadius * mRadFac03
        mArc02.mStartAng = 180F
        mArc02.mSweepAng = 180F
        mArc02.mNum = 2
        mArc02.mHeadTrack333.mTrack = mArc04
        mArc02.mTailTrack333.mTrack = mArc06
        mArc02.mHeadTrack000.mTrack = mStraight78
        mArc02.mTailTrack000.mTrack = mStraight76

        mArc03.mRefPoint.x = mTopPos.x
        mArc03.mRefPoint.y = mTopPos.y
        mArc03.mRad01 = mRadius
        mArc03.mRad02 = mRadius * mRadFac02
        mArc03.mStartAng = 0F
        mArc03.mSweepAng = 55.58F
        mArc03.mNum = 3
        mArc03.mHeadTrack333.mTrack = mArc13
        mArc03.mTailTrack333.mTrack = mArc01
        mArc03.mHeadTrack888.mTrack = mStraight61
        mArc03.mHeadTrack888.mTwisted = true

        mArc04.mRefPoint.x = mTopPos.x
        mArc04.mRefPoint.y = mTopPos.y
        mArc04.mRad01 = mRadius * mRadFac02
        mArc04.mRad02 = mRadius * mRadFac03
        mArc04.mStartAng = 0F
        mArc04.mSweepAng = 28.1F
        mArc04.mNum = 4
        mArc04.mHeadTrack333.mTrack = mArc14
        mArc04.mTailTrack333.mTrack = mArc02
        mArc04.mHeadTrack888.mTrack = mStraight74
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
        mArc05.mTailTrack888.mTrack = mStraight72

        mArc06.mRefPoint.x = mTopPos.x
        mArc06.mRefPoint.y = mTopPos.y
        mArc06.mRad01 = mRadius * mRadFac02
        mArc06.mRad02 = mRadius * mRadFac03
        mArc06.mStartAng = 135F
        mArc06.mSweepAng = 45F
        mArc06.mNum = 6
        mArc06.mHeadTrack333.mTrack = mArc02
        mArc06.mTailTrack333.mTrack = mArc14
        mArc06.mTailTrack888.mTrack = mStraight71

        mArc07.mRefPoint.x = mBotPos.x
        mArc07.mRefPoint.y = mBotPos.y
        mArc07.mRad01 = mRadius
        mArc07.mRad02 = mRadius * mRadFac02
        mArc07.mStartAng = 0F
        mArc07.mSweepAng = 180F
        mArc07.mNum = 7
        mArc07.mHeadTrack333.mTrack = mArc09
        mArc07.mTailTrack333.mTrack = mArc11
        mArc07.mHeadTrack000.mTrack = mStraight75
        mArc07.mTailTrack000.mTrack = mStraight77

        mArc08.mRefPoint.x = mBotPos.x
        mArc08.mRefPoint.y = mBotPos.y
        mArc08.mRad01 = mRadius * mRadFac02
        mArc08.mRad02 = mRadius * mRadFac03
        mArc08.mStartAng = 0F
        mArc08.mSweepAng = 180F
        mArc08.mNum = 8
        mArc08.mHeadTrack333.mTrack = mArc10
        mArc08.mTailTrack333.mTrack = mArc12
        mArc08.mHeadTrack000.mTrack = mStraight76
        mArc08.mTailTrack000.mTrack = mStraight78

        mArc09.mRefPoint.x = mBotPos.x
        mArc09.mRefPoint.y = mBotPos.y
        mArc09.mRad01 = mRadius
        mArc09.mRad02 = mRadius * mRadFac02
        mArc09.mStartAng = 180F
        mArc09.mSweepAng = 55.58F
        mArc09.mNum = 9
        mArc09.mHeadTrack333.mTrack = mArc15
        mArc09.mTailTrack333.mTrack = mArc07
        mArc09.mHeadTrack888.mTrack = mStraight61

        mArc10.mRefPoint.x = mBotPos.x
        mArc10.mRefPoint.y = mBotPos.y
        mArc10.mRad01 = mRadius * mRadFac02
        mArc10.mRad02 = mRadius * mRadFac03
        mArc10.mStartAng = 180F
        mArc10.mSweepAng = 28.12F
        mArc10.mNum = 10
        mArc10.mHeadTrack333.mTrack = mArc16
        mArc10.mTailTrack333.mTrack = mArc08
        mArc10.mHeadTrack888.mTrack = mStraight73

        mArc11.mRefPoint.x = mBotPos.x
        mArc11.mRefPoint.y = mBotPos.y
        mArc11.mRad01 = mRadius
        mArc11.mRad02 = mRadius * mRadFac02
        mArc11.mStartAng = 315F
        mArc11.mSweepAng = 45F
        mArc11.mNum = 11
        mArc11.mHeadTrack333.mTrack = mArc07
        mArc11.mTailTrack333.mTrack = mArc15
        mArc11.mTailTrack888.mTrack = mStraight71
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
        mArc12.mTailTrack888.mTrack = mStraight72
        mArc12.mTailTrack888.mTwisted = true

        mArc13.mRefPoint.x = mTopPos.x
        mArc13.mRefPoint.y = mTopPos.y
        mArc13.mRad01 = mRadius
        mArc13.mRad02 = mRadius * mRadFac02
        mArc13.mStartAng = 55.58F
        mArc13.mSweepAng = 90F + 45F - 55.58F
        mArc13.mNum = 13
        mArc13.mHeadTrack333.mTrack = mArc05
        mArc13.mTailTrack333.mTrack = mArc03

        mArc14.mRefPoint.x = mTopPos.x
        mArc14.mRefPoint.y = mTopPos.y
        mArc14.mRad01 = mRadius * mRadFac02
        mArc14.mRad02 = mRadius * mRadFac03
        mArc14.mStartAng = 28.13F
        mArc14.mSweepAng = 90F + 45F - 28.13F
        mArc14.mNum = 14
        mArc14.mHeadTrack333.mTrack = mArc06
        mArc14.mTailTrack333.mTrack = mArc04

        mArc15.mRefPoint.x = mBotPos.x
        mArc15.mRefPoint.y = mBotPos.y
        mArc15.mRad01 = mRadius
        mArc15.mRad02 = mRadius * mRadFac02
        mArc15.mStartAng = 180F + 55.58F
        mArc15.mSweepAng = 180F - 45F - 55.58F
        mArc15.mNum = 15
        mArc15.mHeadTrack333.mTrack = mArc11
        mArc15.mTailTrack333.mTrack = mArc09

        mArc16.mRefPoint.x = mBotPos.x
        mArc16.mRefPoint.y = mBotPos.y
        mArc16.mRad01 = mRadius * mRadFac02
        mArc16.mRad02 = mRadius * mRadFac03
        mArc16.mStartAng = 180F + 28.13F
        mArc16.mSweepAng = 90F + 45F - 28.13F
        mArc16.mNum = 16
        mArc16.mHeadTrack333.mTrack = mArc12
        mArc16.mTailTrack333.mTrack = mArc10

        mArc31.mRefPoint.x = mTopPos.x
        mArc31.mRefPoint.y = mTopPos.y
        mArc31.mRad01 = mRadius * mRadFac03
        mArc31.mRad02 = mRadius * mRadFac04
        mArc31.mStartAng = 180F
        mArc31.mSweepAng = 180F
        mArc31.mNum = 31
        mArc31.mHeadTrack333.mTrack = mArc34
        mArc31.mTailTrack333.mTrack = mArc32
        mArc31.mHeadTrack000.mTrack = mStraight51
        mArc31.mTailTrack000.mTrack = mStraight52

        mArc32.mRefPoint.x = mTopPos.x
        mArc32.mRefPoint.y = mTopPos.y
        mArc32.mRad01 = mRadius * mRadFac03
        mArc32.mRad02 = mRadius * mRadFac04
        mArc32.mStartAng = 180F - 20.7F
        mArc32.mSweepAng = 20.7F
        mArc32.mNum = 32
        mArc32.mHeadTrack333.mTrack = mArc31
        mArc32.mTailTrack333.mTrack = mArc33
        mArc32.mTailTrack888.mTrack = mStraight62

        mArc33.mRefPoint.x = mTopPos.x
        mArc33.mRefPoint.y = mTopPos.y
        mArc33.mRad01 = mRadius * mRadFac03
        mArc33.mRad02 = mRadius * mRadFac04
        mArc33.mStartAng = 28.1F
        mArc33.mSweepAng = 90F + 45F - 20.7F + 45F - 28.1F
        mArc33.mNum = 33
        mArc33.mHeadTrack333.mTrack = mArc32
        mArc33.mTailTrack333.mTrack = mArc34

        mArc34.mRefPoint.x = mTopPos.x
        mArc34.mRefPoint.y = mTopPos.y
        mArc34.mRad01 = mRadius * mRadFac03
        mArc34.mRad02 = mRadius * mRadFac04
        mArc34.mStartAng = 0F
        mArc34.mSweepAng = 28.1F
        mArc34.mNum = 34
        mArc34.mHeadTrack333.mTrack = mArc33
        mArc34.mTailTrack333.mTrack = mArc31
        mArc34.mHeadTrack888.mTrack = mStraight73
        mArc34.mHeadTrack888.mTwisted = true

        mArc41.mRefPoint.x = mBotPos.x
        mArc41.mRefPoint.y = mBotPos.y
        mArc41.mRad01 = mRadius * mRadFac03
        mArc41.mRad02 = mRadius * mRadFac04
        mArc41.mStartAng = 0F
        mArc41.mSweepAng = 180F
        mArc41.mNum = 41
        mArc41.mHeadTrack333.mTrack = mArc42
        mArc41.mTailTrack333.mTrack = mArc44
        mArc41.mHeadTrack000.mTrack = mStraight52
        mArc41.mTailTrack000.mTrack = mStraight51

        mArc42.mRefPoint.x = mBotPos.x
        mArc42.mRefPoint.y = mBotPos.y
        mArc42.mRad01 = mRadius * mRadFac03
        mArc42.mRad02 = mRadius * mRadFac04
        mArc42.mStartAng = 180F
        mArc42.mSweepAng = 28.12F
        mArc42.mNum = 42
        mArc42.mHeadTrack333.mTrack = mArc43
        mArc42.mTailTrack333.mTrack = mArc41
        mArc42.mHeadTrack888.mTrack = mStraight74

        mArc43.mRefPoint.x = mBotPos.x
        mArc43.mRefPoint.y = mBotPos.y
        mArc43.mRad01 = mRadius * mRadFac03
        mArc43.mRad02 = mRadius * mRadFac04
        mArc43.mStartAng = 180F + 28.12F
        mArc43.mSweepAng = 90F + 45F - 28.12F + 45F - 20.7F
        mArc43.mNum = 43
        mArc43.mHeadTrack333.mTrack = mArc44
        mArc43.mTailTrack333.mTrack = mArc42

        mArc44.mRefPoint.x = mBotPos.x
        mArc44.mRefPoint.y = mBotPos.y
        mArc44.mRad01 = mRadius * mRadFac03
        mArc44.mRad02 = mRadius * mRadFac04
        mArc44.mStartAng = 360F - 20.7F
        mArc44.mSweepAng = 20.7F
        mArc44.mNum = 44
        mArc44.mHeadTrack333.mTrack = mArc41
        mArc44.mTailTrack333.mTrack = mArc43
        mArc44.mTailTrack888.mTrack = mStraight62
        mArc44.mTailTrack888.mTwisted = true

        mStraight71.mNum = 17
        mStraight71.mTwisted = true
        mStraight71.mHeadPoint = mArc06
        mStraight71.mTailPoint = mArc11
        mStraight71.mHeadTrack333.mTrack = mArc06
        mStraight71.mTailTrack333.mTrack = mArc11
        mStraight71.mTailTrack333.mTwisted = true

        mStraight72.mNum = 18
        mStraight72.mTwisted = true
        mStraight72.mHeadPoint = mArc05
        mStraight72.mTailPoint = mArc12
        mStraight72.mHeadTrack333.mTrack = mArc05
        mStraight72.mTailTrack333.mTrack = mArc12
        mStraight72.mTailTrack333.mTwisted = true

        mStraight73.mNum = 19
        mStraight73.mHeadPoint = mArc33
        mStraight73.mTailPoint = mArc16
        mStraight73.mTwisted = true
        mStraight73.mHeadTrack333.mTrack = mArc34
        mStraight73.mTailTrack333.mTrack = mArc10
        mStraight73.mHeadTrack333.mTwisted = true

        mStraight74.mNum = 20
        mStraight74.mHeadPoint = mArc14
        mStraight74.mTailPoint = mArc43
        mStraight74.mTwisted = true
        mStraight74.mHeadTrack333.mTrack = mArc04
        mStraight74.mTailTrack333.mTrack = mArc42
        mStraight74.mHeadTrack333.mTwisted = true

        mStraight75.mNum = 21
        mStraight75.mHeadPoint = mArc01
        mStraight75.mTailPoint = mArc09
        mStraight75.mHeadTrack333.mTrack = mArc01
        mStraight75.mTailTrack333.mTrack = mArc07

        mStraight76.mNum = 22
        mStraight76.mHeadPoint = mArc02
        mStraight76.mTailPoint = mArc10
        mStraight76.mHeadTrack333.mTrack = mArc02
        mStraight76.mTailTrack333.mTrack = mArc08

        mStraight77.mNum = 23
        mStraight77.mHeadPoint = mArc07
        mStraight77.mTailPoint = mArc03
        mStraight77.mHeadTrack333.mTrack = mArc07
        mStraight77.mTailTrack333.mTrack = mArc01

        mStraight78.mNum = 24
        mStraight78.mHeadPoint = mArc08
        mStraight78.mTailPoint = mArc04
        mStraight78.mHeadTrack333.mTrack = mArc08
        mStraight78.mTailTrack333.mTrack = mArc02

        mStraight51.mNum = 51
        mStraight51.mHeadPoint = mArc41
        mStraight51.mTailPoint = mArc34
        mStraight51.mHeadTrack333.mTrack = mArc41
        mStraight51.mTailTrack333.mTrack = mArc31

        mStraight52.mNum = 52
        mStraight52.mHeadPoint = mArc31
        mStraight52.mTailPoint = mArc42
        mStraight52.mHeadTrack333.mTrack = mArc31
        mStraight52.mTailTrack333.mTrack = mArc41

        mStraight61.mNum = 61
        mStraight61.mTwisted = true
        mStraight61.mHeadPoint = mArc13
        mStraight61.mTailPoint = mArc15
        mStraight61.mHeadTrack333.mTrack = mArc03
        mStraight61.mHeadTrack333.mTwisted = true
        mStraight61.mTailTrack333.mTrack = mArc09

        mStraight62.mNum = 62
        mStraight62.mTwisted = true
        mStraight62.mHeadPoint = mArc32
        mStraight62.mTailPoint = mArc44
        mStraight62.mHeadTrack333.mTrack = mArc32
        mStraight62.mTailTrack333.mTrack = mArc44
        mStraight62.mTailTrack333.mTwisted = true
    }
}