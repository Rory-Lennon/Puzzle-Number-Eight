package net.blackseedapps.puzzle8.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Canvas
import android.graphics.RectF
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import net.blackseedapps.puzzle8.GameThread
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.Puzzle8View
import net.blackseedapps.puzzle8.R
import net.blackseedapps.puzzle8.game.GameState
import net.blackseedapps.puzzle8.game.GameViewModel
import net.blackseedapps.puzzle8.game.Vec2D

class GameFragment : Fragment(), SurfaceHolder.Callback, View.OnTouchListener, View.OnKeyListener {
    private val LOG_TAG = "GameFragment "
    lateinit var mGameViewModel : GameViewModel
    lateinit var mNavController: NavController
    var mGameThread: GameThread? = null
    var mNavaway = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setFocusableInTouchMode(true)
        view.requestFocus()
        view.setOnKeyListener(this)
        val sv = view.findViewById<View>(R.id.puzzle8View) as Puzzle8View
        sv.holder.addCallback(this)
        sv.setOnTouchListener(this)
        mNavController = Navigation.findNavController(view)
        mGameViewModel = ViewModelProviders.of(requireActivity())[GameViewModel::class.java]
        mGameViewModel.mFragMsg.observe(viewLifecycleOwner, fragMsg)
    }
    val fragMsg = Observer<Int> {
        if(it == 2) writeScores()
    }
    fun writeScores(){
        (activity as MainActivity).writeDBCoroutine()
    }
    fun draw(canvas: Canvas) {
        mGameViewModel.draw(canvas)
    }
    fun update() {
        mGameViewModel.update()
    }
    override fun surfaceCreated(holder: SurfaceHolder) {
        logThread("surfaceCreated")
        mGameThread = GameThread(holder, this)
        sThreadNo++
        mGameThread?.name = "GameThread" + sThreadNo.toString()
        mGameThread?.setRunning(true)
        mGameThread?.start()
        mGameThread?.waitUntilReady()
    }
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
        }
        return false;
    }
    override fun onPause() {
        super.onPause()
        logThread("onPause ")
        (activity as MainActivity).writeDBCoroutine()
        mGameThread?.setRunning(false)
    }
    fun pauseGame(){
        GameViewModel.sGameState = GameState.PAUSED

        val vol = MainActivity.sSoundVol.toFloat() * 0.25F
        MainActivity.sSoundPool.play(MainActivity.sSoundMorse, vol, vol, 0, 0, 1F)
    }
    fun unPauseGame(){
        GameViewModel.sGameState = GameState.PLAYING
    }
    override fun onResume() {
        mNavaway = false
        logThread("onResume")
        super.onResume()
        mGameThread?.setRunning(true)
//        doFullScreen()
    }
    fun doFullScreen(){
        gameFragment.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    }
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        logThread("surfaceChanged")
        if(width <= 0 || height <= 0) return
        sWidth = width.toFloat()
        sHeight = height.toFloat()
        sScale = sHeight / 2400F
        mGameViewModel.surfaceChanged()
    }
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        logThread("surfaceDestroyed")
        try {
            mGameThread?.join()
        } catch (ie: InterruptedException) {
            throw RuntimeException("Thread join was interrupted")
        }
        mGameThread = null
    }
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        val pointerIndex = event!!.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val maskedAction = event.actionMasked
        val eventVec = Vec2D()
        eventVec.x = event.getX(pointerIndex)
        eventVec.y = event.getY(pointerIndex)
        when (maskedAction) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                when (GameViewModel.sGameState) {
                    GameState.PLAYING -> {
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mModeButton01.mRectButton)) pauseGame()
                        mGameViewModel.mouseDown(eventVec)
                        return true
                    }
                    GameState.PAUSED -> {
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mModeButton01.mRectButton)) unPauseGame()
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuPaused.mRectButtonResumeGame)) unPauseGame()
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuPaused.mRectButtonMainMenu)) {
                            GameViewModel.sGameState = GameState.MAINMENU
                            writeScores()
                        }
                        return true
                    }
                    GameState.MAINMENU -> {
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuMain.mRectButtonPlay)) {
                            GameViewModel.sGameState = GameState.SELECTMENU
                        }
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuMain.mRectButtonHow)) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://www.blackseedapps.net/how-to-play-puzzle-number-eight/")
                            try {
                                startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                Toast.makeText(activity, "No Browser Available", Toast.LENGTH_SHORT).show()
                            }
                        }
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuMain.mRectButtonOptions)) {
                            if(!mNavaway) {
                                mNavaway = true
                                mNavController.navigate(R.id.action_gameFragment_to_optionsFragment)
                            }
                        }
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuMain.mRectButtonExit)) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                        return true
                    }
                    GameState.SELECTMENU -> {
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuSelectTrack.mRectButtonSelect01)) {
                            mGameViewModel.setGame(1)
                            mGameViewModel.mGame.playGame()
                        }
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuSelectTrack.mRectButtonSelect02)) {
                            mGameViewModel.setGame(2)
                            mGameViewModel.mGame.playGame()
                        }
                        if (withinRect(eventVec, mGameViewModel.mScreenGraphics.mMenuSelectTrack.mRectButtonSelect03)) {
                            mGameViewModel.setGame(3)
                            mGameViewModel.mGame.playGame()
                        }
                    }
                    GameState.GAMEOVER -> {}
                }
            }
        }
        return true
    }
    fun withinRect(eventVec: Vec2D, rect: RectF) : Boolean {
        if((eventVec.x > rect.left) and (eventVec.x < rect.right) and
            (eventVec.y > rect.top) and (eventVec.y < rect.bottom)) return true
        return false
    }
    companion object {
        @JvmStatic var sThreadNo = 0
        @JvmStatic var sWidth : Float = 0F
        @JvmStatic var sHeight : Float = 0F
        @JvmStatic var sScale : Float = 1.0F
    }
    private fun logThread(methodName: String) {
        println("GameFragment >>>>>>>>>>>>>>> ${methodName}: ${Thread.currentThread().name}")
    }
}