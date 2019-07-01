package youmeee.co.jp.hatenarssreaderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import youmeee.co.jp.hatenarssreaderapp.databinding.FragmentNewDetailBinding

class NewDetailFragment : Fragment() {

    lateinit var binding: FragmentNewDetailBinding
    val args: NewDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_detail, container, false)
        binding.entry = args.entry
        return binding.root
    }
}
