package youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import youmeee.co.jp.hatenarssreaderapp.core.Injectable
import youmeee.co.jp.hatenarssreaderapp.legacy.R
import youmeee.co.jp.hatenarssreaderapp.legacy.databinding.FragmentNewListBinding
import youmeee.co.jp.hatenarssreaderapp.legacy.ui.adapter.TopViewPagerAdapter
import javax.inject.Inject

class NewListFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentNewListBinding

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var viewPagerAdapter: TopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = TopViewPagerAdapter(requireContext(), childFragmentManager)
        binding.viewPager.apply {
            offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
            adapter = viewPagerAdapter
        }
        binding.tabLayout.apply {
            setupWithViewPager(binding.viewPager)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {
                    //no-op
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                    //no-op
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val bundle = Bundle().also {
                        it.putString("view_type", youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType.fromValue(tab!!.position).typeName)
                    }
                    firebaseAnalytics.logEvent("click_tab", bundle)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                return false
            }
        }
        return true
    }

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4
    }
}
