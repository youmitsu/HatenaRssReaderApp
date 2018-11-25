package youmeee.co.jp.hatenarssreaderapp.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import youmeee.co.jp.hatenarssreaderapp.net.RssListEntity
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yumitsuhori on 2018/11/25.
 */
class TopViewModel @Inject constructor(private val repository: RssRepository) : ViewModel() {

    private lateinit var items: LiveData<RssListEntity>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
//        scope.launch(Dispatchers.IO) {
//            repository.getRss()
//        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    class Factory(val repository: RssRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopViewModel(repository) as T
        }
    }
}