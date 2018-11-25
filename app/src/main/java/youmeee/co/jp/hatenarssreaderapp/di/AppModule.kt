package youmeee.co.jp.hatenarssreaderapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.presenter.TopPresenter
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import javax.inject.Singleton

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl("http://b.hatena.ne.jp/")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(OkHttpClient())
                    .build()

    @Provides
    @Singleton
    fun provideRssApi(retrofit: Retrofit): RssApi =
            retrofit.create(RssApi::class.java)

    @Provides
    @Singleton
    fun provideRssRepository(rssApi: RssApi): RssRepository = RssRepository(rssApi)

    @Provides
    @Singleton
    fun provideTopPresenter(respository: RssRepository) =
            TopPresenter(respository)
}