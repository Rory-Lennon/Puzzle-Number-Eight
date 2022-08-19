package net.blackseedapps.puzzle8

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.yodo1.mas.Yodo1Mas
import com.yodo1.mas.banner.Yodo1MasBannerAdView
import com.yodo1.mas.error.Yodo1MasError
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig
import com.yodo1.nohttp.UserAgent.instance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.blackseedapps.puzzle8.gamedb.GameDataDB
import net.blackseedapps.puzzle8.gamedb.GameDataEntity

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "MainActivity "

    lateinit var bannerAdView : Yodo1MasBannerAdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        readDBCoroutine()
        setContentView(R.layout.activity_main)

        yodo1AgeDialog()
        initYodo1Ads()

        sBlueLightOn = BitmapFactory.decodeResource(
            application.resources,
            R.drawable.lightbulbblue100
        )
        sWhiteFlashBitmap = BitmapFactory.decodeResource(application.resources, R.drawable.flash150)
        sFlashBitmapRed = BitmapFactory.decodeResource(
            application.resources,
            R.drawable.flashred100
        )
        sFlashBitmapGreen = BitmapFactory.decodeResource(
            application.resources,
            R.drawable.flashgreen100
        )
        sOrangeflash = BitmapFactory.decodeResource(
            application.resources,
            R.drawable.orangeflash100
        )
        sImage333 = BitmapFactory.decodeResource(application.resources, R.drawable.image33320001)
        sImage000 = BitmapFactory.decodeResource(application.resources, R.drawable.image00020001)
        sImage888 = BitmapFactory.decodeResource(application.resources, R.drawable.image88820004)
        sImagePause = BitmapFactory.decodeResource(
            application.resources,
            R.drawable.imagepause20005
        )
        sImagePlay = BitmapFactory.decodeResource(application.resources, R.drawable.imageplay20004)
        sBitmapRunner = BitmapFactory.decodeResource(application.resources, R.drawable.runner33200)
        sRunner000 = BitmapFactory.decodeResource(application.resources, R.drawable.t000)
        sRunner000b = BitmapFactory.decodeResource(application.resources, R.drawable.t000b)
        sRunner030 = BitmapFactory.decodeResource(application.resources, R.drawable.t030)
        sRunner030b = BitmapFactory.decodeResource(application.resources, R.drawable.t030b)
        sRunner060 = BitmapFactory.decodeResource(application.resources, R.drawable.t060)
        sRunner060b = BitmapFactory.decodeResource(application.resources, R.drawable.t060b)
        sRunner090 = BitmapFactory.decodeResource(application.resources, R.drawable.t090)
        sRunner090b = BitmapFactory.decodeResource(application.resources, R.drawable.t090b)
        sRunner120 = BitmapFactory.decodeResource(application.resources, R.drawable.t120)
        sRunner120b = BitmapFactory.decodeResource(application.resources, R.drawable.t120b)
        sRunner150 = BitmapFactory.decodeResource(application.resources, R.drawable.t150)
        sRunner150b = BitmapFactory.decodeResource(application.resources, R.drawable.t150b)
        sRunner180 = BitmapFactory.decodeResource(application.resources, R.drawable.t180)
        sRunner180b = BitmapFactory.decodeResource(application.resources, R.drawable.t180b)
        sRunner090j = BitmapFactory.decodeResource(application.resources, R.drawable.t090j)
        sBitmapHoop = BitmapFactory.decodeResource(application.resources, R.drawable.hoop23)
        sGradientRed = BitmapFactory.decodeResource(application.resources, R.drawable.rgred03)
        sGradientGreen = BitmapFactory.decodeResource(application.resources, R.drawable.rggreen03)

        var paint = Paint()
        paint.typeface = ResourcesCompat.getFont(this, R.font.aldrich)
        sTypefaceScore = paint.typeface
        paint.typeface = ResourcesCompat.getFont(this, R.font.great_vibes)
        sTypefaceLogo = paint.typeface

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        sSoundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()

        sSoundCricket01 = sSoundPool.load(this, R.raw.cricket01, 1)
        sSoundCricket02 = sSoundPool.load(this, R.raw.cricket02, 1)
        sSoundSonar = sSoundPool.load(this, R.raw.sonar, 1)
        sSoundImpact = sSoundPool.load(this, R.raw.impact, 1)
        sSoundClocks = sSoundPool.load(this, R.raw.clocks, 1)
        sSoundBump = sSoundPool.load(this, R.raw.bump01, 4)
        sSoundMorse = sSoundPool.load(this, R.raw.morse, 1)
        sSoundMachine = sSoundPool.load(this, R.raw.machine, 1)
        sSoundGameOver = sSoundPool.load(this, R.raw.gameover, 1)
    }

    private fun initYodo1Ads() {

        Yodo1Mas.getInstance().init(this, "k8LPVweBBw", object : Yodo1Mas.InitListener {
            override fun onMasInitSuccessful() {
//                Toast.makeText(this@MainActivity, "[Yodo1 Mas] Successful initialization", Toast.LENGTH_SHORT).show()
            }
            override fun onMasInitFailed(error: Yodo1MasError) {
//                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        bannerAdView = findViewById(R.id.yodo1_mas_banner)
        bannerAdView.loadAd()
    }

    private fun yodo1AgeDialog() {
        val config = Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build()
        Yodo1Mas.getInstance().setAdBuildConfig(config)
    }

    fun readDBCoroutine() =
        CoroutineScope(Job() + Dispatchers.IO).launch {
            var hsDB: GameDataDB = GameDataDB.getInstance(applicationContext)
            hsDB.gameDataDAO().readHS().forEach {
                sHighScore01 = it.highScore01
                sHighScore02 = it.highScore02
                sHighScore03 = it.highScore03
                sSpeedSetting = it.gameSpeed
                sAgreePrivacy = it.agreePrivacy
                sAgreeTCs = it.agreeTCs
                sFirstTimeEver = it.firstTimeEver
                sSoundVol = it.soundVol
            }
            if(sSpeedSetting == 0) sGameSpeed = 0.33F
            if(sSpeedSetting == 1) sGameSpeed = 0.66F
            if(sSpeedSetting == 2) sGameSpeed = 1.00F
            if(sSpeedSetting == 3) sGameSpeed = 1.33F
            if(sSpeedSetting == 4) sGameSpeed = 1.66F
        }
    fun writeDBCoroutine() =
        CoroutineScope(Job() + Dispatchers.IO).launch {
            if (sSpeedSetting == 0) sGameSpeed = 0.33F
            if (sSpeedSetting == 1) sGameSpeed = 0.66F
            if (sSpeedSetting == 2) sGameSpeed = 1.00F
            if (sSpeedSetting == 3) sGameSpeed = 1.33F
            if (sSpeedSetting == 4) sGameSpeed = 1.66F

            var hsDB: GameDataDB = GameDataDB.getInstance(application)
            val hs1 = GameDataEntity(
                1,
                sHighScore01,
                sHighScore02,
                sHighScore03,
                sSpeedSetting,
                sAgreePrivacy,
                sAgreeTCs,
                sFirstTimeEver,
                sSoundVol
            )
            hsDB.gameDataDAO().saveHS(hs1)
        }

    companion object {
        lateinit var sBlueLightOn : Bitmap
        lateinit var sWhiteFlashBitmap : Bitmap
        lateinit var sFlashBitmapRed : Bitmap
        lateinit var sFlashBitmapGreen : Bitmap
        lateinit var sOrangeflash : Bitmap
        lateinit var sGradientRed : Bitmap
        lateinit var sGradientGreen : Bitmap
        lateinit var sImage333 : Bitmap
        lateinit var sImage888 : Bitmap
        lateinit var sImage000 : Bitmap
        lateinit var sImagePause : Bitmap
        lateinit var sImagePlay : Bitmap
        lateinit var sBitmapRunner : Bitmap
        lateinit var sBitmapHoop : Bitmap
        lateinit var sRunner000 : Bitmap
        lateinit var sRunner000b : Bitmap
        lateinit var sRunner030 : Bitmap
        lateinit var sRunner030b : Bitmap
        lateinit var sRunner060 : Bitmap
        lateinit var sRunner060b : Bitmap
        lateinit var sRunner090 : Bitmap
        lateinit var sRunner090b : Bitmap
        lateinit var sRunner120 : Bitmap
        lateinit var sRunner120b : Bitmap
        lateinit var sRunner150 : Bitmap
        lateinit var sRunner150b : Bitmap
        lateinit var sRunner180 : Bitmap
        lateinit var sRunner180b : Bitmap
        lateinit var sRunner090j : Bitmap
        lateinit var sTypefaceScore : Typeface
        lateinit var sTypefaceLogo : Typeface
        var sSoundCricket01 : Int = 0
        var sSoundCricket02 : Int = 0
        var sSoundSonar : Int = 0
        var sSoundImpact : Int = 0
        var sSoundClocks : Int = 0
        var sSoundBump : Int = 0
        var sSoundMorse : Int = 0
        var sSoundMachine : Int = 0
        var sSoundGameOver : Int = 0
        var sHighScore01 = 0
        var sHighScore02 = 0
        var sHighScore03 = 0
        var sSpeedSetting = 2
        var sAgreePrivacy = false
        var sAgreeTCs = false
        var sFirstTimeEver = true
        var sSoundVol = 1
        var sGameSpeed = 1F
        lateinit var sSoundPool : SoundPool
    }
}
