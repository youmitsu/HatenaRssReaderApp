package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.hatenarssreaderapp.BuildConfig
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
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

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics
    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig
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
        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val bundle = Bundle().also {
                    it.putString("view_type", ViewType.fromValue(tab!!.position).typeName)
                }
                firebaseAnalytics.logEvent("click_tab", bundle)
            }
        })

        initRemoteConfigEventSettings()

        showWelcomeMessage()
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

    private fun showWelcomeMessage() {
        fetchWelcomeMessage { title, message ->
            val fragment = WelcomeDialogFragment(title, message)
            fragment.show(supportFragmentManager, DIALOG_TAG)
        }
    }

    private fun fetchWelcomeMessage(isFetched: (shouldShowMessage: String, message: String) -> Unit) {
        remoteConfig.apply {
            fetchAndActivate().addOnCompleteListener {
                isFetched(getString("welcome_title"), getString("welcome_message"))
                sharedPreferences.edit().putBoolean("CONFIG_STALE", false).apply()
            }
        }
    }
}

class WelcomeDialogFragment(
        private val title: String? = DEFAULT_TITLE,
        private val message: String? = DEFAULT_MESSAGE) : DialogFragment() {

    companion object {
        const val DEFAULT_TITLE = "Hello!!"
        const val DEFAULT_MESSAGE = "Have a nice day!"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("OK") { dialog, id ->
            }
        }
        return builder.create()
    }
}