package youmeee.co.jp.hatenarssreaderapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentListBinding
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.presentation.TopRecyclerViewAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.DetailActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.TopViewModel
import youmeee.co.jp.hatenarssreaderapp.util.ViewType

/**
 * ListFragment
 */
class ListFragment : Fragment(), ListView, SwipeRefreshLayout.OnRefreshListener {

    //    @Inject
//    lateinit var presenter: TopPresenter
    private lateinit var binding: FragmentListBinding
    private lateinit var viewType: ViewType
    private var itemList: MutableList<HatebuEntry> = mutableListOf()
        set(value) {
            field.clear()
            for (item in value.sortedByDescending { ZonedDateTime.parse(it.date).toEpochSecond() }) {
                field.add(item)
            }
        }

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

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
        binding.isLoading = true
        binding.isError = false
        //presenter.setView(this)
        val viewModel = ViewModelProviders.of(requireActivity()).get(TopViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener(this)
        coroutineScope.launch {
            //itemList = presenter.loadRss(viewType).items ?: mutableListOf()
            binding.recyclerView.adapter = TopRecyclerViewAdapter(context!!,
                    { entry: HatebuEntry ->
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.ENTRY_KEY, entry)
                        startActivity(intent)
                    }, itemList)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(activity).orientation))
            binding.isLoading = false
        }
    }

    override fun setData(items: MutableList<HatebuEntry>?) {
        this.itemList = items ?: mutableListOf()
        recycler_view.adapter?.notifyDataSetChanged()
    }

    override fun showErrorBar() {
        binding.isError = true
    }

    override fun onRefresh() {
        coroutineScope.launch {
            binding.isError = false
            //setData(presenter.loadRss(viewType).items)
            swipeRefreshLayout.isRefreshing = false
        }
    }

}