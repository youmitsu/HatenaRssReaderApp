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
import youmeee.co.jp.hatenarssreaderapp.util.FAILED
import youmeee.co.jp.hatenarssreaderapp.util.SUCCESS
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val repository: RssRepository
) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

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
            val data = repository.getRss(viewType).value.items ?: mutableListOf()
            mEntries.value = data
            isError.value = when (data) {
                is SUCCESS<*> -> false
                is FAILED<*> -> true
                else -> false
            }
            isLoading.value = false
        }
    }
}