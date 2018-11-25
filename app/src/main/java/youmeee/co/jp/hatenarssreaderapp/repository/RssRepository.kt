package youmeee.co.jp.hatenarssreaderapp.repository

import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.withContext
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.net.RssListEntity
import javax.inject.Inject

/**
 * Created by yumitsuhori on 2018/11/25.
 */
class RssRepository @Inject constructor(
        private val rssApi: RssApi
) {

    suspend fun getRss(): List<RssListEntity> = withContext(Dispatchers.Default) {
        rssApi.getEntry().await()
    }

}