package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry

/**
 * Created by yumitsuhori on 2018/11/23.
 */
class TopRecyclerViewAdapter(
        private val context: Context,
        private val itemClickListener: (view: View, hatebuEntry: HatebuEntry) -> Unit,
        private var itemList: List<HatebuEntry>) : RecyclerView.Adapter<TopRecyclerViewAdapter.TopRecyclerViewHolder>() {

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecyclerViewHolder {
        val layouteInflater = LayoutInflater.from(context)
        val view = layouteInflater.inflate(R.layout.fragment_list_item, parent, false)
        view.setOnClickListener {
            mRecyclerView?.let {
                itemClickListener.invoke(view, itemList[it.getChildAdapterPosition(view)])
            }
        }
        return TopRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TopRecyclerViewHolder, position: Int) {
        val entry = itemList[position]
        holder.apply {
            textView.text = entry.description
            titleView.text = entry.title
            Glide.with(context).load(entry.imageurl).into(thumbnailView)
        }
    }

//    fun setItemList(items: List<HatebuEntry>) {
//        items.forEach {
//            this.itemList.add(it)
//        }
//        notifyDataSetChanged()
//    }


    class TopRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val textView: TextView = view.findViewById(R.id.text)
        val thumbnailView: ImageView = view.findViewById(R.id.thumbnail)
    }

}