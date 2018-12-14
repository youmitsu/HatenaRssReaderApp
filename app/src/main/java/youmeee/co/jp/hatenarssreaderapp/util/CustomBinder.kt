package youmeee.co.jp.hatenarssreaderapp.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import youmeee.co.jp.hatenarssreaderapp.R

/**
 * CustomBinder
 */

private val requestOptions = RequestOptions()

@BindingAdapter("app:imageUrl")
fun ImageView.setImageUrl(url: String) {
    requestOptions.error(R.drawable.no_image)
    Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this)
}

@BindingAdapter("app:dateForString")
fun TextView.setDateForString(date: String) {
    val d = ZonedDateTime.parse(date)
    val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.detail_date_format_pattern))
    text = d.format(formatter)
}