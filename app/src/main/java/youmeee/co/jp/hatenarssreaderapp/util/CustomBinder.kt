package youmeee.co.jp.hatenarssreaderapp.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import youmeee.co.jp.hatenarssreaderapp.R

/**
 * CustomBinder
 */
class CustomBinder {

    companion object {
        private val requestOptions = RequestOptions()

        @SuppressLint("CheckResult")
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun imageUrl(imageView: ImageView, url: String) {
            requestOptions.error(R.drawable.no_image)
            Glide.with(imageView.context)
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView)
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