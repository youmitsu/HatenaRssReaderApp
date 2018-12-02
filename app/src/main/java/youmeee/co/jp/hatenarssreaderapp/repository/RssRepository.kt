package youmeee.co.jp.hatenarssreaderapp.repository

import android.support.annotation.WorkerThread
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject

/**
 * Created by yumitsuhori on 2018/11/25.
 */
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    @WorkerThread
    suspend fun getRss(viewType: ViewType): HatebuFeed =
            when (viewType) {
                ViewType.ALL -> rssApi.getEntry().await()
                ViewType.SOCIAL -> rssApi.getSocialEntry().await()
                ViewType.ECONOMICS -> rssApi.getEconomicsEntry().await()
                ViewType.LIFE -> rssApi.getLifeEntry().await()
            }

}