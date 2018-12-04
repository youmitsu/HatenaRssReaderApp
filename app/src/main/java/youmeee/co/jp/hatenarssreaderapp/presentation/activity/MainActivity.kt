package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var viewPgaerAdapter: TopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPgaerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        view_pager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        view_pager.adapter = viewPgaerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
