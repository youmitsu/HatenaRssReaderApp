package youmeee.co.jp.hatenarssreaderapp.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

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
    }

}