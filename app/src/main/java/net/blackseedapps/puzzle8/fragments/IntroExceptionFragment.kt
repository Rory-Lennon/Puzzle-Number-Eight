package net.blackseedapps.puzzle8.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.navigation.NavController
import androidx.navigation.Navigation
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.R
import net.blackseedapps.puzzle8.game.GameState
import net.blackseedapps.puzzle8.game.GameViewModel

class IntroExceptionFragment : Fragment(), View.OnClickListener {
    private val LOG_TAG = "IntroExceptionFragment "
    lateinit var navController: NavController
    var mNavaway = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_exception, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val cbPrivacy = view.findViewById<CheckBox>(R.id.checkBoxPrivacy2)
        cbPrivacy?.setOnClickListener { v ->
            MainActivity.sAgreePrivacy = (v as CheckBox).isChecked
        }
        cbPrivacy?.isChecked = MainActivity.sAgreePrivacy

        val cbTCs = view.findViewById<CheckBox>(R.id.checkBoxTCs2)
        cbTCs?.setOnClickListener { v ->
            MainActivity.sAgreeTCs = (v as CheckBox).isChecked
        }
        cbTCs?.isChecked = MainActivity.sAgreeTCs

        view.findViewById<Button>(R.id.buttonPlay2)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonExit2)?.setOnClickListener(this)
    }
    override fun onPause(){
        super.onPause()
        (activity as MainActivity).writeDBCoroutine()
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.buttonPlay2 -> {
                if ((MainActivity.sAgreePrivacy == false) or (MainActivity.sAgreeTCs == false)) return
                GameViewModel.sGameState = GameState.SELECTMENU
                if(!mNavaway) {
                    mNavaway = true
                    navController.navigate(R.id.action_introExceptionFragment_to_gameFragment)
                }
            }
            R.id.buttonExit2 -> {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
    }
}