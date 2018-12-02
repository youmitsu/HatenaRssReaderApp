package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.ActivityDetailBinding
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry

/**
 * Created by yumitsuhori on 2018/12/02.
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        val ENTRY_KEY = "entry_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entry = intent.getSerializableExtra(ENTRY_KEY)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        if (entry is HatebuEntry) {
            binding.entry = entry
        }
    }
}