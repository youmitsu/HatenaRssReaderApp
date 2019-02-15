package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter

class MainActivity : BaseActivity() {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
    }

    private lateinit var viewPagerAdapter: TopViewPagerAdapter
//    @Inject
//    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        view_pager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
