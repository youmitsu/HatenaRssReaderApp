package youmeee.co.jp.hatenarssreaderapp.net

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed

/**
 * Created by yumitsuhori on 2018/11/25.
 */
interface RssApi {
    @GET("hotentry.rss")
    fun getEntry(): Deferred<HatebuFeed>

    @GET("hotentry/social")
    fun getSocialEntry()

    @GET("hotentry/economics")
    fun getEconomicsEntry()

    @GET("hotentry/life")
    fun getLifeEntry()
}