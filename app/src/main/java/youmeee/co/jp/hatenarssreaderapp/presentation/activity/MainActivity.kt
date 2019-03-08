package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.os.Bundle
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.clippablelayout.CircleClipEntry
import youmeee.co.jp.clippablelayout.ClipExecutorFactory
import youmeee.co.jp.clippablelayout.ClippableLayout
import youmeee.co.jp.clippablelayout.DescriptionView
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.TutorialTabDescView
import javax.inject.Inject

@Suppress("INACCESSIBLE_TYPE")
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
    }

    private val tutorialLp: FrameLayout.LayoutParams by lazy {
        FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).also {
            it.topMargin = resources.getDimensionPixelSize(R.dimen.tutorial_desc_top_margin)
            it.gravity = CENTER_HORIZONTAL
        }
    }

    private lateinit var viewPagerAdapter: TopViewPagerAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPagerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        view_pager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        ClipExecutorFactory.create(listOf(
                buildClipItem(0, R.string.all_desc),
                buildClipItem(1, R.string.social_desc),
                buildClipItem(2, R.string.society_desc),
                buildClipItem(3, R.string.life_desc)), window, container).execute()
    }

    private fun buildClipItem(id: Int, descResId: Int) = ClippableLayout(this,
            listOf(CircleClipEntry(tab_layout.getTabAt(id)?.view as LinearLayout, resources.getDimension(R.dimen.tab_clip_margin))),
            DescriptionView(TutorialTabDescView(this, getString(descResId)), tutorialLp))
}
