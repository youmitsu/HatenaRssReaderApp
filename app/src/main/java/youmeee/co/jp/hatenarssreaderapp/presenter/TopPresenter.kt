package youmeee.co.jp.hatenarssreaderapp.presenter

import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.presentation.view.ListView
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import youmeee.co.jp.hatenarssreaderapp.util.ApiResult
import youmeee.co.jp.hatenarssreaderapp.util.FAILED
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject


/**
 * TopPresenter
 */
class TopPresenter @Inject constructor(
        val repository: RssRepository
) {
    private lateinit var view: ListView

    suspend fun loadRss(viewType: ViewType): HatebuFeed {
        val response: ApiResult<HatebuFeed> = repository.getRss(viewType)
        if (response is FAILED) {
            view.showErrorBar()
        }
        return response.value
    }

    fun setView(view: ListView) {
        this.view = view
    }
}