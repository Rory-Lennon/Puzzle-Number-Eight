package net.blackseedapps.puzzle8.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment
import kotlin.math.sin
import kotlin.random.Random

enum class TravelMode { MODEPAUSE, MODE000, MODE333, MODE888 }

open class Game {
    private val LOG_TAG = "Game "

    val mPlayerObject = PlayerObject()
    val mScoreObject01 = ScoreObject()
    val mScoreObject02 = ScoreObject()
    var mSurfaceChanged = false

    var mThickFac = 1F
    var mScore = 0
    var mMaxScore = 1
    var mLivesLeft = 0
    var mRadius = 0F
    var mThickness = 0F
    val mTopPos = Vec2D()
    val mBotPos = Vec2D()
    val mLowerBotPos = Vec2D()
    var mRadFac02 = 0.75F
    var mRadFac03 = 0.5F
    var mRadFac04 = 0.25F
    var mAllTracks = Array<Track>(0, {Track()})
    var mTracks333 = Array<Track>(0, {Track()})
    var mTracks000 = Array<Track>(0, {Track()})
    var mTracks888 = Array<Track>(0, {Track()})
    var mTracksMiddleX = Array<Track>(0, {Track()})

    fun getFraction() : Float {
        if(mMaxScore <= 0) return 0F
        else return (mLivesLeft).toFloat() / mMaxScore.toFloat()
    }
    open fun checkHighScore(){}

    open fun getHighScore() : Int {
        return 0
    }
    fun playGame(){
        initialiseGame()
        GameViewModel.sGameState = GameState.PLAYING
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundMachine, vol, vol, 0, 0, 1F)
    }
    open fun initialiseGame(){
        mScore = 0
        mLivesLeft = mMaxScore
        GameViewModel.sSpeedRamp = 1F

        var rndm = Random.nextInt(3)
        if(rndm == 0) sTravelMode = TravelMode.MODE333
        if(rndm == 1) sTravelMode = TravelMode.MODE888
        if(rndm == 2) sTravelMode = TravelMode.MODE000

        rndm = Random.nextInt(2)
        if(rndm == 0) mPlayerObject.mDirectionOfTravel = true
        else mPlayerObject.mDirectionOfTravel = false

        rndm = Random.nextInt(2)
        if(rndm == 0) {
            mScoreObject01.mDirectionOfTravel = true
            mScoreObject02.mDirectionOfTravel = true
        }
        else{
            mScoreObject01.mDirectionOfTravel = false
            mScoreObject02.mDirectionOfTravel = false
        }
    }
    open fun surfaceChanged(){
        sPaint.style = Paint.Style.STROKE
        for(track in mAllTracks) track.surfaceChanged()
        mPlayerObject.mRadius = mRadius / 6F
        mScoreObject01.mRadius = mRadius / 12F
        mScoreObject02.mRadius = mRadius / 12F
        mPlayerObject.setMaxTrailLength(0)
        mPlayerObject.surfaceChanged()
        mScoreObject01.surfaceChanged()
        mScoreObject01.setMaxTrailLength(0)
        mScoreObject02.surfaceChanged()
        mScoreObject02.setMaxTrailLength(0)
        MainActivity.sFirstTimeEver = false
        mSurfaceChanged = true
    }
    fun draw(canvas: Canvas) {
        if(!mSurfaceChanged) return
        mThickFac = 1F
        setPaintBackTrackGrey()
        for(track in mTracksMiddleX) track.drawTrackBack(canvas)
        setPaintBackTrackWhite()
        when(sTravelMode) {
            TravelMode.MODE333 -> for (track in mTracks333) track.drawTrackBack(canvas)
            TravelMode.MODE000 -> for (track in mTracks000) track.drawTrackBack(canvas)
            TravelMode.MODE888 -> for (track in mTracks888) track.drawTrackBack(canvas)
            TravelMode.MODEPAUSE -> {}
        }
        setPaintMinor()
        for(track in mAllTracks) track.drawTrack(canvas)
        setPaintMajor()
        when(sTravelMode) {
            TravelMode.MODE333 -> for (track in mTracks333) track.drawTrack(canvas)
            TravelMode.MODE000 -> for (track in mTracks000) track.drawTrack(canvas)
            TravelMode.MODE888 -> for (track in mTracks888) track.drawTrack(canvas)
            TravelMode.MODEPAUSE -> {}
        }
        setPaintMajorThin()
        when(sTravelMode) {
            TravelMode.MODE333 -> for (track in mTracks333) track.drawTrack(canvas)
            TravelMode.MODE000 -> for (track in mTracks000) track.drawTrack(canvas)
            TravelMode.MODE888 -> for (track in mTracks888) track.drawTrack(canvas)
            TravelMode.MODEPAUSE -> {}
        }
        mScoreObject01.draw(canvas)
        mScoreObject02.draw(canvas)
        mPlayerObject.draw(canvas)

//        for (track in mAllTracks) track.drawNumberAtCentroid(canvas)
    }
    fun drawFromMenu(canvas: Canvas) {
        mThickFac = 0.4F
        setPaintBackTrackGrey()
        for(track in mTracksMiddleX) track.drawTrackBack(canvas)
        setPaintBackTrackWhite()
        for (track in mTracks888) track.drawTrackBack(canvas)
        setPaintMinor()
        for(track in mAllTracks) track.drawTrack(canvas)
        setPaintMajor()
        for (track in mTracks888) track.drawTrack(canvas)
        setPaintMajorThin()
        for (track in mTracks888) track.drawTrack(canvas)
    }
    fun update() {
        if(!mSurfaceChanged) return

        mPlayerObject.update()
        mPlayerObject.mJumping = false
        mScoreObject01.update()
        mScoreObject02.update()

        if(GameViewModel.sGameState == GameState.GAMEOVER) return
        if(GameViewModel.sGameState == GameState.MAINMENU) return
        if(GameViewModel.sGameState == GameState.SELECTMENU) return

        if(mPlayerObject.collisionDection(mScoreObject01)){
            mPlayerObject.mJumping = true
            if(mScoreObject01.mCollisionBlip > 0) return
            mScoreObject01.collision(mPlayerObject.mVelocity)
            mScore += mScoreObject01.mScoreVal
            mLivesLeft -= 1
            if(mScoreObject01.mScoreVal == 1) {
                posSound()
                mScoreObject01.mGreenMushroomBlip = 75
                mScoreObject02.mRedFlashBlip = 75
                mScoreObject01.mScoreVal = -1
                mScoreObject02.mScoreVal = 1
            }
            else {
                negSound()
                mScoreObject01.mRedMushroomBlip = 75
                mScoreObject02.mGreenFlashBlip = 75
                mScoreObject01.mScoreVal = 1
                mScoreObject02.mScoreVal = -1
            }
        }
        if(mPlayerObject.collisionDection(mScoreObject02)) {
            mPlayerObject.mJumping = true
            if (mScoreObject02.mCollisionBlip > 0) return
            mScoreObject02.collision(mPlayerObject.mVelocity)
            mScore += mScoreObject02.mScoreVal
            mLivesLeft -= 1
            if(mScoreObject02.mScoreVal == 1) {
                posSound()
                mScoreObject02.mGreenMushroomBlip = 100
                mScoreObject01.mRedFlashBlip = 100
                mScoreObject02.mScoreVal = -1
                mScoreObject01.mScoreVal = 1
            }
            else {
                negSound()
                mScoreObject02.mRedMushroomBlip = 100
                mScoreObject01.mGreenFlashBlip = 100
                mScoreObject02.mScoreVal = 1
                mScoreObject01.mScoreVal = -1
            }
        }
    }
    fun posSound() {
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundCricket02, vol, vol, 0, 0, 1F)
    }
    fun negSound() {
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundCricket01, vol, vol, 0, 0, 1F)
    }
    fun setPaintBackTrackGrey() {
        sPaint.color = Color.rgb(200, 200, 200)
        sPaint.strokeWidth = mThickness
    }
    fun setPaintBackTrackWhite() {
        sPaint.color = Color.rgb(255, 255, 255)
        sPaint.strokeWidth = mThickness
    }
    fun setPaintMinor() {
        sPaint.color = Color.rgb(175, 175, 175)
        sPaint.strokeWidth = mThickFac * GameFragment.sHeight / 175F
    }
    fun setPaintMajor() {
        sPaint.color = Color.RED
        sPaint.strokeWidth = mThickFac * GameFragment.sHeight / 125F
    }
    fun setPaintMajorThin() {
        sPaint.color = Color.rgb(255, 255, 255)
        sPaint.strokeWidth = mThickFac * GameFragment.sHeight / 500F
    }
    fun mouseDown(eventVec : Vec2D){
        val vec = Vec2D()
        vec.x = eventVec.x
        vec.y = eventVec.y
        if((vec.y > mTopPos.y) && (vec.y < mBotPos.y)) {
            val y1 = mTopPos.x - mRadius
            val y2 = mTopPos.x - mRadius * mRadFac03
            val y3 = mTopPos.x + mRadius * mRadFac03
            val y4 = mTopPos.x + mRadius
            if((vec.x > y1) && (vec.x < y2)) changeTo000()
            if((vec.x > y2) && (vec.x < y3)) changeTo888()
            if((vec.x > y3) && (vec.x < y4)) changeTo000()
            return
        }
        val lenTop = vec.getGeometricDistance(mTopPos)
        val lenBot = vec.getGeometricDistance(mBotPos)
        if((lenTop < mRadius) or (lenBot < mRadius)) changeTo333()
    }
    fun changeTo333(){
        sTravelMode = TravelMode.MODE333
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundMorse, vol, vol, 0, 0, 1F)
    }
    fun changeTo888(){
        sTravelMode = TravelMode.MODE888
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundMorse, vol, vol, 0, 0, 1F)
    }
    fun changeTo000(){
        sTravelMode = TravelMode.MODE000
        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundMorse, vol, vol, 0, 0, 1F)
    }
    companion object {
        @JvmStatic var sTravelMode = TravelMode.MODE333
        @JvmStatic var sPaint = Paint()
    }
}