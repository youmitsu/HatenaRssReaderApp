package youmeee.co.jp.hatenarssreaderapp.legacy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import youmeee.co.jp.hatenarssreaderapp.legacy.R
import youmeee.co.jp.hatenarssreaderapp.legacy.net.entity.HatebuEntry

/**
 * TopRecyclerViewAdapter
 */
class TopRecyclerViewAdapter(
        private val context: Context,
        private val itemClickListener: (hatebuEntry: HatebuEntry) -> Unit
) : RecyclerView.Adapter<TopRecyclerViewAdapter.TopRecyclerViewHolder>() {

    var itemList: List<HatebuEntry>? = listOf()
    private var mRecyclerView: RecyclerView? = null
    private val requestOptions = RequestOptions()

    init {
        requestOptions.error(R.drawable.no_image)
        requestOptions.fitCenter()
    }

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
            mRecyclerView?.let { r ->
                itemList?.let { e -> itemClickListener.invoke(e[r.getChildAdapterPosition(view)]) }
            }
        }
        return TopRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: TopRecyclerViewHolder, position: Int) {
        val entry = itemList?.get(position) ?: HatebuEntry()
        holder.apply {
            titleView.text = entry.title
            youmeee.co.jp.hatenarssreaderapp.legacy.util.CustomBinder.dateForString(date, entry.date)
            Glide.with(context)
                    .load(entry.imageurl)
                    .apply(requestOptions)
                    .into(thumbnailView)
        }
    }

    class TopRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val thumbnailView: ImageView = view.findViewById(R.id.thumbnail)
        val date: TextView = view.findViewById(R.id.date)
    }

}