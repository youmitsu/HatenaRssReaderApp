package youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

class MainViewModel @Inject constructor(
        val repository: RssRepository
) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val entries: LiveData<List<HatebuEntry>>
        get() {
            return mEntries
        }
    private val mEntries = MutableLiveData<List<HatebuEntry>>()

    fun loadRss(viewType: ViewType) {
        scope.launch {
            mEntries.value = repository.getRss(viewType).value.items
        }
    }
}