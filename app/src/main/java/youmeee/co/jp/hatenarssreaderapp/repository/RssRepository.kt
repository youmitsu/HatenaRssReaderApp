package youmeee.co.jp.hatenarssreaderapp.repository

import android.support.annotation.WorkerThread
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed
import javax.inject.Inject

/**
 * Created by yumitsuhori on 2018/11/25.
 */
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    @WorkerThread
    suspend fun getRss(): HatebuFeed =
            rssApi.getEntry().await()

}