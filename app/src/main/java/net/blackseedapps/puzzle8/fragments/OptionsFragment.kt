package net.blackseedapps.puzzle8.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.R

class OptionsFragment : Fragment(), View.OnClickListener {
    private val LOG_TAG = "OptionsFragment "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateChks()
        view.findViewById<Button>(R.id.buttonPlay2)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonResetHighScore)?.setOnClickListener(this)

        val sb = view.findViewById<SeekBar>(R.id.seekBar3)
        sb?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {
                MainActivity.sSpeedSetting = seek.progress
            }
        })
        val seekBarSoundVol = view.findViewById<SeekBar>(R.id.seekBarSoundVol)
        seekBarSoundVol?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {
                MainActivity.sSoundVol = seek.progress
            }
        })
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.buttonPlay2 -> requireActivity().onBackPressed()
            R.id.buttonResetHighScore -> {
                MainActivity.sHighScore01 = 0
                MainActivity.sHighScore02 = 0
                MainActivity.sHighScore03 = 0
                (activity as MainActivity).writeDBCoroutine()
            }
        }
    }
    override fun onPause() {
        super.onPause()
        (activity as MainActivity).writeDBCoroutine()
    }
    private fun updateChks(){
        val sb = view?.findViewById<SeekBar>(R.id.seekBar3)
        sb?.progress = MainActivity.sSpeedSetting
        val seekBarSoundVol = view?.findViewById<SeekBar>(R.id.seekBarSoundVol)
        seekBarSoundVol?.progress = MainActivity.sSoundVol
    }
}
