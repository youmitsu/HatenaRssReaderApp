package youmeee.co.jp.hatenarssreaderapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import youmeee.co.jp.hatenarssreaderapp.App
import youmeee.co.jp.hatenarssreaderapp.legacy.di.LegacyAppModule
import youmeee.co.jp.hatenarssreaderapp.legacy.di.MainModule
import youmeee.co.jp.hatenarssreaderapp.legacy.di.UiModule
import javax.inject.Singleton

/**
 * AppComponent
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    LegacyAppModule::class,
    UiModule::class,
    MainModule::class,
    FirebaseModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}