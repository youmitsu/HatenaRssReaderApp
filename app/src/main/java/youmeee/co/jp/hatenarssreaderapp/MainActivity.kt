package youmeee.co.jp.hatenarssreaderapp

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var viewPgaerAdapter: TopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        viewPgaerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = viewPgaerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
