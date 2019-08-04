package youmeee.co.jp.hatenarssreaderapp.legacy.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import youmeee.co.jp.hatenarssreaderapp.legacy.net.entity.HatebuEntry
import youmeee.co.jp.hatenarssreaderapp.legacy.data.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.legacy.util.FAILED
import youmeee.co.jp.hatenarssreaderapp.legacy.util.SUCCESS
import youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val repository: youmeee.co.jp.hatenarssreaderapp.legacy.data.repository.RssRepository
) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main)

    val entries: LiveData<MutableList<HatebuEntry>>
        get() {
            return mEntries
        }
    private val mEntries = MutableLiveData<MutableList<HatebuEntry>>(mutableListOf())
    val isLoading = ObservableBoolean(false)
    val isError = ObservableBoolean(false)

    fun loadRss(viewType: youmeee.co.jp.hatenarssreaderapp.legacy.util.ViewType) {
        scope.launch {
            isLoading.set(true)
            val data = withContext(Dispatchers.Default) {
                repository.getRss(viewType)
            }
            mEntries.value = data.value.items
            isError.set(when (data) {
                is youmeee.co.jp.hatenarssreaderapp.legacy.util.SUCCESS -> false
                is youmeee.co.jp.hatenarssreaderapp.legacy.util.FAILED -> true
            })
            isLoading.set(false)
        }
    }
}