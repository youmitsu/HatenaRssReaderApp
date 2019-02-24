package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.clippablelayout.CircleClipEntry
import youmeee.co.jp.clippablelayout.ClipExecutorFactory
import youmeee.co.jp.clippablelayout.ClippableItem
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import javax.inject.Inject

@Suppress("INACCESSIBLE_TYPE")
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
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
        val tab1 = tab_layout.getTabAt(0)?.view
        val entry = CircleClipEntry(tab1 as LinearLayout, resources.getDimension(R.dimen.tab_clip_margin))
        val item = ClippableItem(this, listOf(entry))
        ClipExecutorFactory.create(listOf(item), window, container).execute()
    }
}
