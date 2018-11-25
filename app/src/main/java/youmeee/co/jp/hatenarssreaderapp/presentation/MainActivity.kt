package youmeee.co.jp.hatenarssreaderapp.presentation

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.viewModel.TopViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: TopViewModel.Factory

    lateinit var model: TopViewModel

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var viewPgaerAdapter: TopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //model = ViewModelProviders.of(this, viewModelFactory)[TopViewModel::class.java]

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        viewPgaerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = viewPgaerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
