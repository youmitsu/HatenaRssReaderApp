package youmeee.co.jp.hatenarssreaderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentNewListBinding
import youmeee.co.jp.hatenarssreaderapp.di.Injectable
import youmeee.co.jp.hatenarssreaderapp.presentation.TopViewPagerAdapter
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

class NewListFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentNewListBinding

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var viewPagerAdapter: TopViewPagerAdapter

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
                        it.putString("view_type", ViewType.fromValue(tab!!.position).typeName)
                    }
                    firebaseAnalytics.logEvent("click_tab", bundle)
                }
            })
        }
    }

    companion object {
        private const val OFF_SCREEN_PAGE_LIMIT = 4

        private const val DIALOG_TAG = "default"

        private const val DEFAULT_FETCH_TIME = 43200L
    }
}
