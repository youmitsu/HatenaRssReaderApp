package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import youmeee.co.jp.hatenarssreaderapp.App
import javax.inject.Singleton

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class
])
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}