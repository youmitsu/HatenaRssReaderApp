package youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import youmeee.co.jp.hatenarssreaderapp.legacy.R
import youmeee.co.jp.hatenarssreaderapp.legacy.databinding.FragmentNewDetailBinding

class NewDetailFragment : Fragment() {

    lateinit var binding: FragmentNewDetailBinding
    private val args: NewDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_detail, container, false)
        setupNavigationBar(null, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.entry = args.entry
        binding.handler = this
    }

    override fun onDetach() {
        setupNavigationBar(resources.getString(R.string.app_name), false)
        super.onDetach()
    }

    private fun setupNavigationBar(title: String?, isShowUp: Boolean) {
        (requireActivity() as AppCompatActivity).apply {
            supportActionBar?.title = title
            supportActionBar?.setDisplayHomeAsUpEnabled(isShowUp)
        }
    }

    fun onClickMoreRead(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }
}
