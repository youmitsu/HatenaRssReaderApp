package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
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
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TopRecyclerViewAdapter

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    lateinit var data: List<HatebuEntry>

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.setView(this)
        viewType = ViewType.fromValue(arguments.getInt(VIEW_TYPE_KEY))
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_list, null)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        scope.launch {
            data = presenter.loadRss(viewType).items ?: mutableListOf()
            adapter = TopRecyclerViewAdapter(context,
                    { v: View, i: Int -> Toast.makeText(context, "position is ${i}", Toast.LENGTH_LONG) }, data)
            recyclerView.adapter = adapter
        }

        return view
    }

    override fun setData(hatebuFeed: HatebuFeed) {
//        data = hatebuFeed.items ?: mutableListOf()
    }

}