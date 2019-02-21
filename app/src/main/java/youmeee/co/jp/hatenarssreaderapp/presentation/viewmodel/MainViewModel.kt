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

    val entries: LiveData<MutableMap<ViewType, MutableList<HatebuEntry>>>
        get() {
            return mEntries
        }
    private val mEntries = MutableMapLiveData()
    private val isLoading = MutableLiveData<Boolean>(false)

    fun loadRss(viewType: ViewType) {
        isLoading.value = true
        scope.launch {
            val data = repository.getRss(viewType).value.items ?: mutableListOf()
            mEntries.setValueWithKey(viewType, data)
            isLoading.value = false
        }
    }

    class MutableMapLiveData : MutableLiveData<MutableMap<ViewType, MutableList<HatebuEntry>>>() {

        init {
            value = mutableMapOf(
                    ViewType.ALL to mutableListOf(),
                    ViewType.SOCIAL to mutableListOf(),
                    ViewType.ECONOMICS to mutableListOf(),
                    ViewType.LIFE to mutableListOf()
            )
        }

        fun setValueWithKey(viewType: ViewType, list: Collection<HatebuEntry>) {
            value?.get(viewType)?.addAll(list)
            super.setValue(value)
        }
    }
}