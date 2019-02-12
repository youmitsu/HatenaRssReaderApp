package youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.util.ApiResult

open class TopViewModel : ViewModel() {

    val result: LiveData<ApiResult<HatebuFeed>>
        get() {
            return mResult
        }
    val mResult = MutableLiveData<ApiResult<HatebuFeed>>()


}