package youmeee.co.jp.hatenarssreaderapp.net

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

/**
 * Created by yumitsuhori on 2018/11/25.
 */
interface RssApi {
    @GET("hotentry")
    fun getEntry(): Deferred<List<RssListEntity>>

    @GET("hotentry/social")
    fun getSocialEntry()

    @GET("hotentry/economics")
    fun getEconomicsEntry()

    @GET("hotentry/life")
    fun getLifeEntry()
}