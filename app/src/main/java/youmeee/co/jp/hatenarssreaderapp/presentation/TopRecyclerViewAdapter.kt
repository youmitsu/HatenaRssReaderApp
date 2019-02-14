package youmeee.co.jp.hatenarssreaderapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.util.CustomBinder

/**
 * TopRecyclerViewAdapter
 */
class TopRecyclerViewAdapter(
        private val context: Context,
        private val itemClickListener: (hatebuEntry: HatebuEntry) -> Unit,
        private val itemList: List<HatebuEntry>?) : RecyclerView.Adapter<TopRecyclerViewAdapter.TopRecyclerViewHolder>() {

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

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TopRecyclerViewHolder, position: Int) {
        val entry = itemList[position]
        holder.apply {
            titleView.text = entry.title
            CustomBinder.dateForString(date, entry.date)
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