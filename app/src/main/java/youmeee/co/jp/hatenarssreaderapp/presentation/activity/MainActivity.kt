package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.TopViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
    }

    private lateinit var viewPagerAdapter: TopViewPagerAdapter
    @Inject
    lateinit var viewModel: TopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerAdapter = TopViewPagerAdapter(this, supportFragmentManager)
        view_pager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
