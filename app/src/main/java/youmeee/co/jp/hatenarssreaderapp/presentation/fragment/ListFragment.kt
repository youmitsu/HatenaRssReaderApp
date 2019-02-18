package youmeee.co.jp.hatenarssreaderapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_list.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentListBinding
import youmeee.co.jp.hatenarssreaderapp.di.Injectable
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.presentation.TopRecyclerViewAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.DetailActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.MainViewModel
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

/**
 * ListFragment
 */
class ListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentListBinding
    private lateinit var viewType: ViewType
    private lateinit var viewModel: MainViewModel

    companion object {
        val VIEW_TYPE_KEY = "view_type"

        fun newInstance(viewType: ViewType): Fragment {
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
            viewType = ViewType.fromValue(it.getInt(VIEW_TYPE_KEY))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
        viewModel.entries.observe(this, Observer {
            binding.recyclerView.adapter.notifyDataSetChanged()
        })
        swipeRefreshLayout.setOnRefreshListener(this)
        binding.recyclerView.adapter = TopRecyclerViewAdapter(context!!,
                { entry: HatebuEntry ->
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ENTRY_KEY, entry)
                    startActivity(intent)
                },
                viewModel.entries.value)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(activity).orientation))
        binding.isLoading = true
        binding.isError = false
        viewModel.loadRss()
        binding.isLoading = false
    }

    override fun onRefresh() {
        binding.isError = false
        swipeRefreshLayout.isRefreshing = false
    }

}