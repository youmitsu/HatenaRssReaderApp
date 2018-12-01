package youmeee.co.jp.hatenarssreaderapp.presenter

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext


/**
 * Created by yumitsuhori on 2018/11/25.
 */
class TopPresenter @Inject constructor(
        val repository: RssRepository
) {
    private lateinit var view: ListView

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun loadRss(viewType: ViewType) {
        scope.launch(Dispatchers.Main) {
            val itemList = repository.getRss(viewType)
            view.showData(itemList)
            return@launch
        }
    }

    fun setView(view: ListView) {
        this.view = view
    }
}