package net.blackseedapps.puzzle8.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yodo1.mas.Yodo1Mas
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig
import net.blackseedapps.puzzle8.MainActivity
import net.blackseedapps.puzzle8.R
import net.blackseedapps.puzzle8.game.GameState
import net.blackseedapps.puzzle8.game.GameViewModel

class IntroFragment : Fragment(), View.OnClickListener {
    private val LOG_TAG = "IntroFragment "
    lateinit var navController: NavController
    var mNavaway = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }
    fun checkNav() {
        if((MainActivity.sAgreePrivacy == true) and (MainActivity.sAgreeTCs == true)) {
            GameViewModel.sGameState = GameState.MAINMENU
            if(!mNavaway) {
                mNavaway = true
                navController.navigate(R.id.action_introFragment_to_gameFragment)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        checkNav()

        val config = Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build()
        Yodo1Mas.getInstance().setAdBuildConfig(config)

        val cbPrivacy = view.findViewById<CheckBox>(R.id.checkBoxPrivacy)
        cbPrivacy?.setOnClickListener { v ->
            MainActivity.sAgreePrivacy = (v as CheckBox).isChecked
        }
        cbPrivacy?.isChecked = MainActivity.sAgreePrivacy

        val cbTCs = view.findViewById<CheckBox>(R.id.checkBoxTCs)
        cbTCs?.setOnClickListener { v ->
            MainActivity.sAgreeTCs = (v as CheckBox).isChecked
        }
        cbTCs?.isChecked = MainActivity.sAgreeTCs

        view.findViewById<Button>(R.id.buttonPrivacy)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonTCs)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonPlay)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonExit)?.setOnClickListener(this)
    }
    override fun onPause(){
        super.onPause()
        (activity as MainActivity).writeDBCoroutine()
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.buttonPrivacy -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.blackseedapps.net/privacy-policy/")
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    mNavaway = true
                    navController.navigate(R.id.action_introFragment_to_introExceptionFragment)
                }
            }
            R.id.buttonTCs -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data =
                    Uri.parse("https://www.blackseedapps.net/puzzle-number-eight-terms-of-use/")
                try {
                    startActivity(openURL)
                } catch (e: ActivityNotFoundException) {
                    mNavaway = true
                    navController.navigate(R.id.action_introFragment_to_introExceptionFragment)
                }
            }
            R.id.buttonPlay -> {
                if ((MainActivity.sAgreePrivacy == false) or (MainActivity.sAgreeTCs == false)) return
                GameViewModel.sGameState = GameState.SELECTMENU
                if(!mNavaway) {
                    mNavaway = true
                    navController.navigate(R.id.action_introFragment_to_gameFragment)
                }
            }
            R.id.buttonExit -> {
                mNavaway = true
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
    }
}
