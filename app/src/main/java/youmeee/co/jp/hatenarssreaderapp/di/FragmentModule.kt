package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.fragment.ListFragment

/**
 * FragmentModule
 */
@Module
abstract class FragmentModule {

    // Activityと同様に@ContributesAndroidInjectorを定義する

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): ListFragment
}