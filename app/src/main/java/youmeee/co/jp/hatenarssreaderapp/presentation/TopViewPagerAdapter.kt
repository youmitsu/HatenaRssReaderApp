package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.util.ViewType

/**
 * Created by yumitsuhori on 2018/11/23.
 */
class TopViewPagerAdapter(val context: Context,
                          fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabCount = 4

    override fun getItem(position: Int): Fragment {
        return ListFragment.newInstance(ViewType.fromValue(position))
    }

    override fun getCount(): Int = tabCount

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.category_all)
            1 -> context.getString(R.string.category_social)
            2 -> context.getString(R.string.category_economics)
            3 -> context.getString(R.string.category_life)
            else -> context.getString(R.string.category_all)
        }
    }

}