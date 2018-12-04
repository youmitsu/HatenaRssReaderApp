package youmeee.co.jp.hatenarssreaderapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import youmeee.co.jp.hatenarssreaderapp.BuildConfig
import youmeee.co.jp.hatenarssreaderapp.net.RssApi
import youmeee.co.jp.hatenarssreaderapp.presenter.TopPresenter
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import javax.inject.Singleton

/**
 * AppModule
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        }
                    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl("http://b.hatena.ne.jp/")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(client)
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