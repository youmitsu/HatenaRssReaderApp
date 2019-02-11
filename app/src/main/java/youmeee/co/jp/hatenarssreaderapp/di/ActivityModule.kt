package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.MainActivity

/**
 * ActivityModule
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}