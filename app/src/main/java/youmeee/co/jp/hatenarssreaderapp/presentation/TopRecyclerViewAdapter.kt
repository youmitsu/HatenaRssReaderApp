package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import youmeee.co.jp.hatenarssreaderapp.R

/**
 * Created by yumitsuhori on 2018/11/23.
 */
class TopRecyclerViewAdapter(
        private val context: Context,
        private val itemClickListener: TopRecyclerViewHolder.ItemClickListener,
        private val itemList: List<String>) : RecyclerView.Adapter<TopRecyclerViewAdapter.TopRecyclerViewHolder>() {

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TopRecyclerViewHolder {
        val layouteInflater = LayoutInflater.from(context)
        val view = layouteInflater.inflate(R.layout.fragment_list_item, parent, false)
        view.setOnClickListener {
            itemClickListener.onClickCard(view, mRecyclerView.getChildAdapterPosition(view))
        }
        return TopRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TopRecyclerViewHolder?, position: Int) {
        //TODO: RSSのPOJOにする
        holder?.apply {
            textView.text = itemList[position]
            titleView.text = itemList[position]
        }
    }


    class TopRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView
        val titleView: TextView
        val textView: TextView

        init {
            iconView = view.findViewById(R.id.icon)
            titleView = view.findViewById(R.id.title)
            textView = view.findViewById(R.id.text)
        }

        interface ItemClickListener {
            fun onClickCard(view: View, position: Int)
        }
    }

}