package net.blackseedapps.puzzle8

import android.view.SurfaceHolder
import android.graphics.Canvas
import android.util.Log
import net.blackseedapps.puzzle8.fragments.GameFragment
import net.blackseedapps.puzzle8.game.GameViewModel

class GameThread(holder: SurfaceHolder, val gF: GameFragment) : Thread() {
    private val LOG_TAG = "GameThread "
    private val mStartLock = Object()
    private var mReady = false
    private val mSurfaceHolder: SurfaceHolder = holder
    private val mTargetFPS = 60 // frames per second, the rate at which you would like to refresh the Canvas
    private var mRunning: Boolean = false

    fun setRunning(isRunning: Boolean)
    {
        mRunning = isRunning
    }

    override fun run() {

        logThread("run")
//        Looper.prepare()
//        mHandler = GDHandler(this)

        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / mTargetFPS).toLong()

        synchronized(mStartLock) {
            mReady = true
            mStartLock.notify() // signal waitUntilReady()
        }

        while (mRunning)
        {
            startTime = System.nanoTime()
//            Log.i("GDThread", " >>>>>>>>>>>>>>>>>>>> running")

            try {
                mCanvas =  mSurfaceHolder.lockCanvas()
//                mCanvas = mSurfaceHolder.lockHardwareCanvas()
//                val startTime02 = System.nanoTime()
                if(mCanvas != null) gF.draw(mCanvas!!)
//                val endTime02 = System.nanoTime()
//                val nanoTimeTaken02 = endTime02 - startTime02
//                val timeTaken02 = nanoTimeTaken02 / 1e9
//                if(GameFragment.logBlip == 5) Log.d(LOG_TAG, "mGF.draw(mCanvas!!) $timeTaken02")
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
            finally {
                if(mCanvas != null) mSurfaceHolder.unlockCanvasAndPost(mCanvas)
                mCanvas = null
            }
            gF.update()

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis
            if(waitTime < 0) waitTime = 1
            try {
                sleep(waitTime)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        synchronized(mStartLock) {
            mReady = false
        }
    }

    fun waitUntilReady() {
        synchronized(mStartLock) {
            while (!mReady) {
                try {
                    mStartLock.wait()
                } catch (ie: InterruptedException) {
                    ie.printStackTrace()
                }
            }
        }
    }
    companion object
    {
        private var mCanvas: Canvas? = null
        @JvmStatic var skipFrame = 0
    }
    private fun logThread(methodName: String)
    {
        println("GDThread >>>>>>>>>>>>>>> ${methodName}: ${Thread.currentThread().name}")
    }
}