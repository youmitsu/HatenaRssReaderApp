package youmeee.co.jp.hatenarssreaderapp.presentation.fragment

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentListBinding
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.presentation.TopRecyclerViewAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.DetailActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.presenter.TopPresenter
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext


/**
 * ListFragment
 */
class ListFragment : Fragment(), ListView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: TopPresenter
    private lateinit var binding: FragmentListBinding
    private lateinit var viewType: ViewType
    private lateinit var itemList: MutableList<HatebuEntry>

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

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

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        arguments?.let {
            viewType = ViewType.fromValue(it.getInt(VIEW_TYPE_KEY))
        }
        binding.isLoading = true
        presenter.setView(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener(this)
        scope.launch {
            itemList = presenter.loadRss(viewType).items ?: mutableListOf()
            recycler_view.adapter = TopRecyclerViewAdapter(context!!,
                    { v: View, entry: HatebuEntry ->
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.ENTRY_KEY, entry)
                        startActivity(intent)
                    }, itemList)
            recycler_view.layoutManager = LinearLayoutManager(context)
            recycler_view.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(activity).orientation))
            binding.isLoading = false
        }
    }

    override fun setData(items: MutableList<HatebuEntry>?) {
        this.itemList.clear()
        items?.let {
            for (item in it) {
                this.itemList.add(item)
            }
        }
        recycler_view.adapter.notifyDataSetChanged()
    }

    override fun onRefresh() {
        scope.launch {
            setData(presenter.loadRss(viewType).items)
            swipeRefreshLayout.isRefreshing = false
        }
    }
}