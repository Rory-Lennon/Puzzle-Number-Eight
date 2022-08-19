package net.blackseedapps.puzzle8.game

import android.graphics.*
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.fragments.GameFragment

enum class GameState { MAINMENU, SELECTMENU, PLAYING, PAUSED, GAMEOVER }

class GameViewModel : ViewModel() {
    private val LOG_TAG = "GameViewModel "
    var mHandler: Handler = Handler(Looper.getMainLooper())
    var mGameOverBlip = 0
    var mFragMsg = MutableLiveData(0)

    val mGame01 = Game01()
    val mGame02 = Game02()
    val mGame03 = Game03()
    var mGame = mGame03 as Game

    fun setGame(gameNo : Int){

        when(gameNo) {
            1 -> mGame = mGame01
            2 -> mGame = mGame02
            3 -> mGame = mGame03
        }
    }
    val mScreenGraphics : ScreenGraphics = ScreenGraphics()

    var runnableGameOver = Runnable{gameOver()}
    fun gameOver(){
        mFragMsg.value = 1
        mFragMsg.value = 0
    }
    var runnableWriteScores = Runnable{writeScores()}
    fun writeScores(){
        mFragMsg.value = 2
        mFragMsg.value = 0
    }
    fun surfaceChanged(){
        mScreenGraphics.surfaceChanged()
        mGame01.mRadius = GameFragment.sHeight * 0.175F
        mGame01.mTopPos.x = GameFragment.sWidth / 2F
        mGame01.mTopPos.y = GameFragment.sHeight * 0.22F
        mGame01.surfaceChanged()
        mGame02.mRadius = GameFragment.sHeight * 0.15F
        mGame02.mTopPos.x = GameFragment.sWidth / 2F
        mGame02.mTopPos.y = GameFragment.sHeight * 0.2F
        mGame02.surfaceChanged()
        mGame03.mRadius = GameFragment.sHeight * 0.15F
        mGame03.mTopPos.x = GameFragment.sWidth / 2F
        mGame03.mTopPos.y = GameFragment.sHeight * 0.2F
        mGame03.surfaceChanged()
    }
    fun update() {
//        val startTime02 = System.nanoTime()
        if(sGameState == GameState.PAUSED) return
        //////////
        sLogBlip++
        if(sLogBlip > 60) sLogBlip = 0
        //////////////////
        mGameOverBlip--
        if (mGameOverBlip == 0) sGameState = GameState.MAINMENU
        if (mGameOverBlip < 0) mGameOverBlip = 0
        //////////////////
        mGame.update()

        if (sGameState == GameState.MAINMENU) return
        if (sGameState == GameState.SELECTMENU) return
        if (sGameState == GameState.GAMEOVER) return

        if(mGame.mScore < 0) mGame.mScore = 0
        mGame.checkHighScore()
        val ratio = mGame.getFraction()
        sSpeedRamp = 1F + (1F - ratio) * 0.5F
        if(mGame.mLivesLeft < 1) {
            sGameState = GameState.GAMEOVER
            mGameOverBlip = 240
            val vol = MainActivity.sSoundVol.toFloat() * 0.25F
            MainActivity.sSoundPool.play(MainActivity.sSoundGameOver, vol, vol, 0, 0, 1F)
            mHandler.post(runnableWriteScores)
        }
//        val endTime02 = System.nanoTime()
//        val nanoTimeTaken02 = endTime02 - startTime02
//        val timeTaken02 = nanoTimeTaken02 / 1e9
//        if(sLogBlip == 5) Log.d(LOG_TAG, "*********************************** update() $timeTaken02")
    }
    fun draw(canvas: Canvas) {
//        else canvas.drawColor(Color.rgb(0, 0, 0))
        mScreenGraphics.drawGradient(canvas)
        mScreenGraphics.drawButtons(canvas)
        mScreenGraphics.mMenuMain.drawLogo(canvas)
        mScreenGraphics.drawBarBand(canvas)
        mScreenGraphics.drawScore(mGame.mScore, canvas)
        mScreenGraphics.drawLivesLeft(mGame.getFraction(), canvas)
        mGame.draw(canvas)
        if(sGameState == GameState.GAMEOVER) mScreenGraphics.mMenuGameOver.draw(mGame.mScore, mGame.getHighScore(), canvas)
        if(sGameState == GameState.PAUSED) mScreenGraphics.mMenuPaused.draw(mGame.mScore, mGame.getHighScore(), canvas)
        if(sGameState == GameState.MAINMENU) mScreenGraphics.mMenuMain.draw(canvas)
        if(sGameState == GameState.SELECTMENU) mScreenGraphics.mMenuSelectTrack.draw(canvas)
    }
    fun mouseDown(eventVec : Vec2D){
        mGame.mouseDown(eventVec)
        if(withinRect(eventVec, mScreenGraphics.mModeButton02.mRectButton)) mGame.changeTo333()
        if(withinRect(eventVec, mScreenGraphics.mModeButton03.mRectButton)) mGame.changeTo888()
        if(withinRect(eventVec, mScreenGraphics.mModeButton04.mRectButton)) mGame.changeTo000()
    }
    fun withinRect(eventVec: Vec2D, rect: RectF) : Boolean {
        if((eventVec.x > rect.left) and (eventVec.x < rect.right) and
            (eventVec.y > rect.top) and (eventVec.y < rect.bottom)) return true
        return false
    }
    companion object {
        @JvmStatic var sGameState = GameState.PLAYING
        @JvmStatic var sSpeedRamp = 1F
        @JvmStatic var sLogBlip = 0
    }
}