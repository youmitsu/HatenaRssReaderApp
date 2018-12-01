package youmeee.co.jp.hatenarssreaderapp.presenter

import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject


/**
 * Created by yumitsuhori on 2018/11/25.
 */
class TopPresenter @Inject constructor(
        val repository: RssRepository
) {
    private lateinit var view: ListView

//    private val job = Job()
//    private val coroutineContext: CoroutineContext
//        get() = job + Dispatchers.Main
//    private val scope = CoroutineScope(coroutineContext)

    suspend fun loadRss(viewType: ViewType): HatebuFeed = repository.getRss(viewType)

    fun setView(view: ListView) {
        this.view = view
    }
}