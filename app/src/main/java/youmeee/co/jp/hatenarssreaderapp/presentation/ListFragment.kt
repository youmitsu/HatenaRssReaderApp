package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.presenter.TopPresenter
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

/**
 * Created by yumitsuhori on 2018/11/23.
 */
class ListFragment : Fragment() {

    @Inject
    lateinit var presenter: TopPresenter

    lateinit var viewType: ViewType
    lateinit var recyclerView: RecyclerView

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
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.fragment_list, null)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

}