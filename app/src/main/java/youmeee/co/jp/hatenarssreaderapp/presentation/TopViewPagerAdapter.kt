package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.fragment.ListFragment
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import youmeee.co.jp.hatenarssreaderapp.R

/**
 * TopViewPagerAdapter
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