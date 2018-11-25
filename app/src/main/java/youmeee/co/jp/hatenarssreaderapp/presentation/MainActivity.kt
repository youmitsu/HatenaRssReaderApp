package youmeee.co.jp.hatenarssreaderapp.presentation

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import youmeee.co.jp.hatenarssreaderapp.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var viewPgaerAdapter: TopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        viewPgaerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = viewPgaerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
