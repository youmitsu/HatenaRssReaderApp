package youmeee.co.jp.hatenarssreaderapp.presentation.fragment

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentListBinding
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.presentation.TopRecyclerViewAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.DetailActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.presenter.TopPresenter
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext


/**
 * Created by yumitsuhori on 2018/11/23.
 */
class ListFragment : Fragment(), ListView {

    @Inject
    lateinit var presenter: TopPresenter

    lateinit var viewType: ViewType
    lateinit var adapter: TopRecyclerViewAdapter

    lateinit var binding: FragmentListBinding

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    lateinit var data: MutableList<HatebuEntry>

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
        presenter.setView(this)
        arguments?.let {
            viewType = ViewType.fromValue(it.getInt(VIEW_TYPE_KEY))
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        scope.launch {
            binding.hasFixedSize = true
            binding.itemDecoration = DividerItemDecoration(context, LinearLayoutManager(activity).orientation)
            binding.layoutManager = LinearLayoutManager(context)
            data = presenter.loadRss(viewType).items ?: mutableListOf()
            binding.adapter = TopRecyclerViewAdapter(context!!,
                    { v: View, entry: HatebuEntry ->
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.ENTRY_KEY, entry)
                        startActivity(intent)
                    }, data)
        }
        return binding.root
    }

    override fun setData(hatebuFeed: HatebuFeed) {
//        data = hatebuFeed.items ?: mutableListOf()
    }

}