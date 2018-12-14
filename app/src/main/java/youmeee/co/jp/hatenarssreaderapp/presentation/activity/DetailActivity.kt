package youmeee.co.jp.hatenarssreaderapp.presentation.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import youmeee.co.jp.hatenarssreaderapp.R
import youmeee.co.jp.hatenarssreaderapp.databinding.ActivityDetailBinding
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry

/**
 * DetailActivity
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var entry: HatebuEntry

    companion object {
        val ENTRY_KEY = "entry_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entry = intent.getSerializableExtra(ENTRY_KEY) as? HatebuEntry ?: HatebuEntry()
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.entry = entry
        binding.activity = this
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
    
    fun onClick(view: View) {
        val url = Uri.parse(entry.link)
        startActivity(Intent(Intent.ACTION_VIEW, url))
    }
}