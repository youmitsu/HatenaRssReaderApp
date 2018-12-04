package youmeee.co.jp.hatenarssreaderapp.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import youmeee.co.jp.hatenarssreaderapp.R

/**
 * CustomBinder
 */
class CustomBinder {

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun imageUrl(imageView: ImageView, url: Int) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("app:dateForString")
        fun dateForString(textView: TextView, date: String) {
            val date = ZonedDateTime.parse(date)
            val formatter = DateTimeFormatter.ofPattern(textView.context.getString(R.string.detail_date_format_pattern))
            textView.text = date.format(formatter)
        }
    }
}