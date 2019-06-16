package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4

        private const val DIALOG_TAG = "default"

        private const val DEFAULT_FETCH_TIME = 43200L

    }

    private lateinit var viewPagerAdapter: TopViewPagerAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPagerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        view_pager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        showWelcomeMessage()
    }

    private fun fetchWelcomeMessage(isFetched: (message: String) -> Unit) {
        val configSettings = FirebaseRemoteConfigSettings.Builder().also {
            val fetchCacheTime = if (sharedPreferences.getBoolean("CONFIG_STALE", false)) {
                0L
            } else {
                DEFAULT_FETCH_TIME
            }
            it.setMinimumFetchIntervalInSeconds(fetchCacheTime)
        }.build()
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaults(R.xml.remote_config_defaults)
            fetchAndActivate().addOnCompleteListener {
                isFetched(getString("welcome_message"))
            }
        }
    }

    private fun showWelcomeMessage() {
        fetchWelcomeMessage { message ->
            val fragment = WelcomeDialogFragment(message)
            fragment.show(supportFragmentManager, DIALOG_TAG)
            sharedPreferences.edit().putBoolean("CONFIG_STALE", false).apply()
        }
    }
}

class WelcomeDialogFragment(private val message: String? = DEFAULT_MESSAGE) : DialogFragment() {

    companion object {
        const val DEFAULT_MESSAGE = "Hello!!"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("Welcome Message")
            setMessage(message)
            setPositiveButton("OK") { dialog, id ->
            }
        }
        return builder.create()
    }
}