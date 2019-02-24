package youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.util.FAILED
import youmeee.co.jp.hatenarssreaderapp.util.SUCCESS
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val repository: RssRepository
) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main)

    val entries: LiveData<MutableList<HatebuEntry>>
        get() {
            return mEntries
        }
    private val mEntries = MutableLiveData<MutableList<HatebuEntry>>(mutableListOf())
    val isLoading = MutableLiveData(false)
    val isError = MutableLiveData(false)

    fun loadRss(viewType: ViewType) {
        scope.launch {
            isLoading.value = true
            val data = withContext(Dispatchers.Default) {
                repository.getRss(viewType)
            }
            mEntries.value = data.value.items
            isError.value = when (data) {
                is SUCCESS -> false
                is FAILED -> true
            }
            isLoading.value = false
        }
    }
}