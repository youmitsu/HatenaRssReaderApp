package youmeee.co.jp.hatenarssreaderapp.legacy.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.legacy.R

/**
 * TopViewPagerAdapter
 */
class TopViewPagerAdapter(val context: Context,
                          fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabCount = 4

    override fun getItem(position: Int): Fragment {
        return youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment.ListFragment.newInstance(youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType.fromValue(position))
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