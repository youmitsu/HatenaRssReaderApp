package youmeee.co.jp.hatenarssreaderapp.repository

import androidx.annotation.WorkerThread
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import youmeee.co.jp.hatenarssreaderapp.util.ApiResult
import youmeee.co.jp.hatenarssreaderapp.util.FAILED
import youmeee.co.jp.hatenarssreaderapp.util.SUCCESS
import youmeee.co.jp.hatenarssreaderapp.util.ViewType
import javax.inject.Inject
import javax.inject.Singleton

/**
 * RssRepository
 */
@Singleton
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    @WorkerThread
    suspend fun getRss(viewType: ViewType): ApiResult<HatebuFeed> {
        val getEntry = when (viewType) {
            ViewType.ALL -> rssApi.getEntry()
            ViewType.SOCIAL -> rssApi.getSocialEntry()
            ViewType.ECONOMICS -> rssApi.getEconomicsEntry()
            ViewType.LIFE -> rssApi.getLifeEntry()
        }
        return try {
            SUCCESS(getEntry.await())
        } catch (e: Exception) {
            FAILED(HatebuFeed(), e)
        }
    }
}