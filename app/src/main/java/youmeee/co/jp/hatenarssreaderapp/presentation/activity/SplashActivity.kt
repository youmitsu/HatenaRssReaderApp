package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import youmeee.co.jp.hatenarssreaderapp.BuildConfig
import youmeee.co.jp.hatenarssreaderapp.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DEFAULT_FETCH_TIME = 43200L
    }

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initRemoteConfigEventSettings()

        fetchWelcomeMessage { title, message, url ->
            findViewById<TextView>(R.id.title).text = title
            findViewById<TextView>(R.id.message).text = message
            Glide.with(this)
                    .load(url)
                    .listener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            moveToMainActivity()
                            return false
                        }
                    })
                    .apply(RequestOptions.circleCropTransform())
                    .into(findViewById(R.id.splash_image))
        }
    }

    private fun moveToMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }, 1000)
    }

    private fun initRemoteConfigEventSettings() {
        val fetchTime = if (BuildConfig.DEBUG ||
                sharedPreferences.getBoolean("CONFIG_STALE", false)) {
            0L
        } else {
            DEFAULT_FETCH_TIME // デフォルト12時間
        }
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(fetchTime)
                .build()
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaults(R.xml.remote_config_defaults)
        }
    }

    private fun fetchWelcomeMessage(isFetched: (title: String, message: String, url: String) -> Unit) {
        remoteConfig.apply {
            fetchAndActivate().addOnCompleteListener {
                isFetched(getString("welcome_title"),
                        getString("welcome_message"),
                        getString("welcome_image_url"))
                sharedPreferences.edit().putBoolean("CONFIG_STALE", false).apply()
            }
        }
    }

}
