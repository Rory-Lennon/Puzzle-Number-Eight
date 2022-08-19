package net.blackseedapps.puzzle8.game

import android.graphics.Color
import android.util.Log
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

class Game01 : Game() {
    private val LOG_TAG = "Game01 "

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

    val mStraight13 = TrackStraight()
    val mStraight14 = TrackStraight()
    val mStraight15 = TrackStraight()
    val mStraight16 = TrackStraight()

    init {
        mAllTracks = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mStraight13, mStraight14, mStraight15, mStraight16)

        mTracks333 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12)

        mTracks000 = arrayOf(mArc01, mArc02, mArc07, mArc08,
            mStraight13, mStraight14, mStraight15, mStraight16)

        mTracks888 = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12)

        mTracksMiddleX = arrayOf(mArc01, mArc02, mArc03, mArc04, mArc05, mArc06, mArc07, mArc08,
            mArc09, mArc10, mArc11, mArc12, mStraight13, mStraight14, mStraight15, mStraight16)

//        defineTrack()
        initialiseGame()
    }
    override fun initialiseGame() {
        mMaxScore = 20
        super.initialiseGame()
        mPlayerObject.mCurrentTrack = mArc07
        mScoreObject01.mCurrentTrack = mArc01
        mScoreObject01.mScoreVal = -1
        mScoreObject02.mCurrentTrack = mArc02
        mScoreObject02.mScoreVal = 1
    }
    override fun checkHighScore() {
        if(mScore > MainActivity.sHighScore01) MainActivity.sHighScore01 = mScore
    }
    override fun getHighScore() : Int {
        return MainActivity.sHighScore01
    }
    override fun surfaceChanged() {

        Log.i(LOG_TAG, "Game01 ")

        mThickness = mRadius * (1F - mRadFac02)
        mBotPos.x = mTopPos.x
        mBotPos.y = mTopPos.y + (mRadius * mRadFac02) * 2F
        mLowerBotPos.x = GameFragment.sWidth / 2F
        mLowerBotPos.y = mTopPos.y + (mRadius * mRadFac02) * 4F
        defineTrack()
        super.surfaceChanged()
    }
    fun defineTrack() {
        mArc01.mRefPoint.x = mTopPos.x
        mArc01.mRefPoint.y = mTopPos.y
        mArc01.mRad01 = mRadius
        mArc01.mRad02 = mRadius * mRadFac02
        mArc01.mStartAng = 180F
        mArc01.mSweepAng = 180F
        mArc01.mNum = 1
        mArc01.mHeadTrack333.mTrack = mArc03
        mArc01.mTailTrack333.mTrack = mArc05
        mArc01.mHeadTrack000.mTrack = mStraight13
        mArc01.mTailTrack000.mTrack = mStraight15

        mArc02.mRefPoint.x = mTopPos.x
        mArc02.mRefPoint.y = mTopPos.y
        mArc02.mRad01 = mRadius * mRadFac02
        mArc02.mRad02 = mRadius * mRadFac03
        mArc02.mStartAng = 180F
        mArc02.mSweepAng = 180F
        mArc02.mNum = 2
        mArc02.mHeadTrack333.mTrack = mArc04
        mArc02.mTailTrack333.mTrack = mArc06
        mArc02.mHeadTrack000.mTrack = mStraight14
        mArc02.mTailTrack000.mTrack = mStraight16

        mArc03.mRefPoint.x = mTopPos.x
        mArc03.mRefPoint.y = mTopPos.y
        mArc03.mRad01 = mRadius
        mArc03.mRad02 = mRadius * mRadFac02
        mArc03.mStartAng = 0F
        mArc03.mSweepAng = 90F
        mArc03.mNum = 3
        mArc03.mHeadTrack333.mTrack = mArc05
        mArc03.mTailTrack333.mTrack = mArc01
        mArc03.mHeadTrack888.mTrack = mArc10
        mArc03.mHeadTrack888.mTwisted = true

        mArc04.mRefPoint.x = mTopPos.x
        mArc04.mRefPoint.y = mTopPos.y
        mArc04.mRad01 = mRadius * mRadFac02
        mArc04.mRad02 = mRadius * mRadFac03
        mArc04.mStartAng = 0F
        mArc04.mSweepAng = 90F
        mArc04.mNum = 4
        mArc04.mHeadTrack333.mTrack = mArc06
        mArc04.mTailTrack333.mTrack = mArc02
        mArc04.mHeadTrack888.mTrack = mArc09
        mArc04.mHeadTrack888.mTwisted = true

        mArc05.mRefPoint.x = mTopPos.x
        mArc05.mRefPoint.y = mTopPos.y
        mArc05.mRad01 = mRadius
        mArc05.mRad02 = mRadius * mRadFac02
        mArc05.mStartAng = 90F
        mArc05.mSweepAng = 90F
        mArc05.mNum = 5
        mArc05.mHeadTrack333.mTrack = mArc01
        mArc05.mTailTrack333.mTrack = mArc03
        mArc05.mTailTrack888.mTrack = mArc12
        mArc05.mTailTrack888.mTwisted = true

        mArc06.mRefPoint.x = mTopPos.x
        mArc06.mRefPoint.y = mTopPos.y
        mArc06.mRad01 = mRadius * mRadFac02
        mArc06.mRad02 = mRadius * mRadFac03
        mArc06.mStartAng = 90F
        mArc06.mSweepAng = 90F
        mArc06.mNum = 6
        mArc06.mHeadTrack333.mTrack = mArc02
        mArc06.mTailTrack333.mTrack = mArc04
        mArc06.mTailTrack888.mTrack = mArc11
        mArc06.mTailTrack888.mTwisted = true

        mArc07.mRefPoint.x = mBotPos.x
        mArc07.mRefPoint.y = mBotPos.y
        mArc07.mRad01 = mRadius
        mArc07.mRad02 = mRadius * mRadFac02
        mArc07.mStartAng = 0F
        mArc07.mSweepAng = 180F
        mArc07.mNum = 7
        mArc07.mHeadTrack333.mTrack = mArc09
        mArc07.mTailTrack333.mTrack = mArc11
        mArc07.mHeadTrack000.mTrack = mStraight15
        mArc07.mTailTrack000.mTrack = mStraight13

        mArc08.mRefPoint.x = mBotPos.x
        mArc08.mRefPoint.y = mBotPos.y
        mArc08.mRad01 = mRadius * mRadFac02
        mArc08.mRad02 = mRadius * mRadFac03
        mArc08.mStartAng = 0F
        mArc08.mSweepAng = 180F
        mArc08.mNum = 8
        mArc08.mHeadTrack333.mTrack = mArc10
        mArc08.mTailTrack333.mTrack = mArc12
        mArc08.mHeadTrack000.mTrack = mStraight16
        mArc08.mTailTrack000.mTrack = mStraight14

        mArc09.mRefPoint.x = mBotPos.x
        mArc09.mRefPoint.y = mBotPos.y
        mArc09.mRad01 = mRadius
        mArc09.mRad02 = mRadius * mRadFac02
        mArc09.mStartAng = 180F
        mArc09.mSweepAng = 90F
        mArc09.mNum = 9
        mArc09.mHeadTrack333.mTrack = mArc11
        mArc09.mTailTrack333.mTrack = mArc07
        mArc09.mHeadTrack888.mTrack = mArc04
        mArc09.mHeadTrack888.mTwisted = true

        mArc10.mRefPoint.x = mBotPos.x
        mArc10.mRefPoint.y = mBotPos.y
        mArc10.mRad01 = mRadius * mRadFac02
        mArc10.mRad02 = mRadius * mRadFac03
        mArc10.mStartAng = 180F
        mArc10.mSweepAng = 90F
        mArc10.mNum = 10
        mArc10.mHeadTrack333.mTrack = mArc12
        mArc10.mTailTrack333.mTrack = mArc08
        mArc10.mHeadTrack888.mTrack = mArc03
        mArc10.mHeadTrack888.mTwisted = true

        mArc11.mRefPoint.x = mBotPos.x
        mArc11.mRefPoint.y = mBotPos.y
        mArc11.mRad01 = mRadius
        mArc11.mRad02 = mRadius * mRadFac02
        mArc11.mStartAng = 270F
        mArc11.mSweepAng = 90F
        mArc11.mNum = 11
        mArc11.mHeadTrack333.mTrack = mArc07
        mArc11.mTailTrack333.mTrack = mArc09
        mArc11.mTailTrack888.mTrack = mArc06
        mArc11.mTailTrack888.mTwisted = true

        mArc12.mRefPoint.x = mBotPos.x
        mArc12.mRefPoint.y = mBotPos.y
        mArc12.mRad01 = mRadius * mRadFac02
        mArc12.mRad02 = mRadius * mRadFac03
        mArc12.mStartAng = 270F
        mArc12.mSweepAng = 90F
        mArc12.mNum = 12
        mArc12.mHeadTrack333.mTrack = mArc08
        mArc12.mTailTrack333.mTrack = mArc10
        mArc12.mTailTrack888.mTrack = mArc05
        mArc12.mTailTrack888.mTwisted = true

        mStraight13.mNum = 13
        mStraight13.mHeadPoint = mArc07
        mStraight13.mTailPoint = mArc03
        mStraight13.mHeadTrack333.mTrack = mArc07
        mStraight13.mTailTrack333.mTrack = mArc01

        mStraight14.mNum = 14
        mStraight14.mHeadPoint = mArc08
        mStraight14.mTailPoint = mArc04
        mStraight14.mHeadTrack333.mTrack = mArc08
        mStraight14.mTailTrack333.mTrack = mArc02

        mStraight15.mNum = 15
        mStraight15.mHeadPoint = mArc01
        mStraight15.mTailPoint = mArc09
        mStraight15.mHeadTrack333.mTrack = mArc01
        mStraight15.mTailTrack333.mTrack = mArc07

        mStraight16.mNum = 16
        mStraight16.mHeadPoint = mArc02
        mStraight16.mTailPoint = mArc10
        mStraight16.mHeadTrack333.mTrack = mArc02
        mStraight16.mTailTrack333.mTrack = mArc08
    }
}