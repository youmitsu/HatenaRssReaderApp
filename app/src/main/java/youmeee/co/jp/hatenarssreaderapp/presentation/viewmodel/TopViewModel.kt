package youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.util.ApiResult
import youmeee.co.jp.hatenarssreaderapp.util.SUCCESS

open class TopViewModel : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(job)

    val result: LiveData<ApiResult<HatebuFeed>>
        get() {
            return mResult
        }
    private val mResult = MutableLiveData<ApiResult<HatebuFeed>>()

    fun loadRss() {
        scope.launch {
            mResult.value = SUCCESS(HatebuFeed())
        }
    }
}