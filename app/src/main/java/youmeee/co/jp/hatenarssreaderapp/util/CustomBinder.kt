package youmeee.co.jp.hatenarssreaderapp.util

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import youmeee.co.jp.hatenarssreaderapp.presentation.BindableAdapter
import youmeee.co.jp.hatenarssreaderapp.presentation.TopRecyclerViewAdapter

/**
 * Created by yumitsuhori on 2018/12/02.
 */
class CustomBinder {

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun imageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("app:dateForString")
        fun dateForString(textView: TextView, date: String) {
            val date = ZonedDateTime.parse(date)
            val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分")
            textView.text = date.format(formatter)
        }

        @JvmStatic
        @BindingAdapter("app:itemDecoration")
        fun addItemDecoration(view: RecyclerView, decoration: DividerItemDecoration) {
            view.addItemDecoration(decoration)
        }

        @JvmStatic
        @BindingAdapter("app:hasFixedSize")
        fun addItemDecoration(view: RecyclerView, hasFixedSize: Boolean) {
            view.setHasFixedSize(hasFixedSize)
        }

        @JvmStatic
        @BindingAdapter("app:layoutManager")
        fun addItemDecoration(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
            view.layoutManager = layoutManager
        }

        @JvmStatic
        @BindingAdapter("app:recyclerAdapter")
        fun setRecyclerAdapter(view: RecyclerView, adapter: TopRecyclerViewAdapter) {
            view.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("app:data")
        fun <T> setRecyclerView(view: RecyclerView, itemList: T) {
            if (view.adapter is BindableAdapter<*>) {
                (view.adapter as BindableAdapter<T>).setData(itemList)
            }
        }
    }
}