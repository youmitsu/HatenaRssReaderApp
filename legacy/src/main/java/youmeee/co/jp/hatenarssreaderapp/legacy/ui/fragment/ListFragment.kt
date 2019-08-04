package youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_list.*
import youmeee.co.jp.hatenarssreaderapp.core.Injectable
import youmeee.co.jp.hatenarssreaderapp.legacy.R
import youmeee.co.jp.hatenarssreaderapp.legacy.databinding.FragmentListBinding
import youmeee.co.jp.hatenarssreaderapp.legacy.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.legacy.ui.adapter.TopRecyclerViewAdapter
import javax.inject.Inject

/**
 * ListFragment
 */
class ListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, Injectable {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewType: youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType
    private lateinit var viewModel: youmeee.co.jp.hatenarssreaderapp.legacy.ui.viewmodel.MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object {
        val VIEW_TYPE_KEY = "view_type"

        fun newInstance(viewType: youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType): Fragment {
            val fragment = ListFragment()
            val bundle = Bundle()
            bundle.putInt(VIEW_TYPE_KEY, viewType.value)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        arguments?.let {
            viewType = youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType.fromValue(it.getInt(VIEW_TYPE_KEY))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(youmeee.co.jp.hatenarssreaderapp.legacy.ui.viewmodel.MainViewModel::class.java)
        viewModel.entries.observe(this, Observer { list ->
            (binding.recyclerView.adapter as? TopRecyclerViewAdapter)?.itemList = list
            binding.recyclerView.adapter?.notifyDataSetChanged()
        })
        binding.viewType = this.viewType
        binding.viewModel = this.viewModel
        swipeRefreshLayout.setOnRefreshListener(this)
        binding.recyclerView.adapter = TopRecyclerViewAdapter(requireContext()) { entry: HatebuEntry ->
            val bundle = Bundle().also {
                it.putString("view_type", viewType.typeName)
                it.putString("title", entry.title)
                it.putString("url", entry.link)
            }
            firebaseAnalytics.logEvent("click_item", bundle)
            val direction = NewListFragmentDirections.actionNewListFragmentToNewDetailFragment(entry)
            findNavController().navigate(direction)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(activity).orientation))
        viewModel.loadRss(viewType)
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = false
        viewModel.loadRss(viewType)
    }

}