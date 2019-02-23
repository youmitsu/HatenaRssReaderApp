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
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val entries: LiveData<MutableMap<ViewType, MutableList<HatebuEntry>>>
        get() {
            return mEntries
        }
    private val mEntries = MutableMapLiveDataWithViewType<MutableList<HatebuEntry>>(mutableListOf())
    val isLoading = MutableMapLiveDataWithViewType(false)
    val isError = MutableMapLiveDataWithViewType(false)

    fun loadRss(viewType: ViewType) {
        isLoading.setValueWithKey(viewType) {}
        scope.launch {
            val data = repository.getRss(viewType).value.items ?: mutableListOf()
            mEntries.setValueWithKey(viewType, data)
            withContext(Dispatchers.Main) {
                isError.value = when (data) {
                    is SUCCESS<*> -> false
                    is FAILED<*> -> true
                    else -> false
                }
                isLoading.value = false
            }
        }
    }
//
//    class MutableMapLiveData : MutableLiveData<MutableMap<ViewType, MutableList<HatebuEntry>>>() {
//
//        init {
//            value = mutableMapOf(
//                    ViewType.ALL to mutableListOf(),
//                    ViewType.SOCIAL to mutableListOf(),
//                    ViewType.ECONOMICS to mutableListOf(),
//                    ViewType.LIFE to mutableListOf()
//            )
//        }
//
//        fun setValueWithKey(viewType: ViewType, list: Collection<HatebuEntry>) {
//            value?.get(viewType)?.addAll(list)
//            super.setValue(value)
//        }
//    }

    class MutableMapLiveDataWithViewType<T>(initValue: T) : MutableLiveData<MutableMap<ViewType, T>>() {
        init {
            value = mutableMapOf(
                    ViewType.ALL to initValue,
                    ViewType.SOCIAL to initValue,
                    ViewType.ECONOMICS to initValue,
                    ViewType.LIFE to initValue
            )
        }

        fun setValueWithKey(viewType: ViewType, updateValue: (v: T?) -> Unit) {
            updateValue(value?.get(viewType))
            super.setValue(value)
        }
    }
}