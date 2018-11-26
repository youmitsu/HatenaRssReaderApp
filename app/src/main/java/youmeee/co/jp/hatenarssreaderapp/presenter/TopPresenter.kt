package youmeee.co.jp.hatenarssreaderapp.presenter

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
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

    fun loadRss() {
        scope.launch(Dispatchers.IO) {
            val itemList = repository.getRss()
            view.showData(itemList)
            return@launch
        }
    }

    fun setView(view: ListView) {
        this.view = view
    }
}