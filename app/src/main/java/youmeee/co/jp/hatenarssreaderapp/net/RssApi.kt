package youmeee.co.jp.hatenarssreaderapp.net

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed

/**
 * RssApi
 */
interface RssApi {
    @GET("hotentry.rss")
    fun getEntry(): Deferred<HatebuFeed>

    @GET("hotentry/social.rss")
    fun getSocialEntry(): Deferred<HatebuFeed>

    @GET("hotentry/economics.rss")
    fun getEconomicsEntry(): Deferred<HatebuFeed>

    @GET("hotentry/life.rss")
    fun getLifeEntry(): Deferred<HatebuFeed>
}