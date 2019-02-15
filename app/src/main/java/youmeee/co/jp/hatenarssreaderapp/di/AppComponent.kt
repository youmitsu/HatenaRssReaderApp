package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import youmeee.co.jp.hatenarssreaderapp.App
import javax.inject.Singleton

/**
 * AppComponent
 */
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityBuilder::class
])
@Singleton
interface AppComponent : AndroidInjector<App>