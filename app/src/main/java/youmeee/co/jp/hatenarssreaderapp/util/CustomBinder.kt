package youmeee.co.jp.hatenarssreaderapp.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

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
    }

}