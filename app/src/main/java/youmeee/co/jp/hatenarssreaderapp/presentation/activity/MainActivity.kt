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
import youmeee.co.jp.clippablelayout.ClipEntry
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
        val firstTabEntry = buildClipEntry(0)
        val secondTabEntry = buildClipEntry(1)
        val thirdTabEntry = buildClipEntry(2)

        val item = ClippableItem(this, listOf(firstTabEntry))
        val item2 = ClippableItem(this, listOf(secondTabEntry))
        val item3 = ClippableItem(this, listOf(thirdTabEntry))
        ClipExecutorFactory.create(listOf(item, item2, item3), window, container).execute()
    }

    private fun buildClipEntry(id: Int): ClipEntry {
        return CircleClipEntry(tab_layout.getTabAt(id)?.view as LinearLayout, resources.getDimension(R.dimen.tab_clip_margin))
    }
}
