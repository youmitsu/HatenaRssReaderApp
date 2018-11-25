package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.ListFragment

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Module
abstract class FragmentModule {

    // Activityと同様に@ContributesAndroidInjectorを定義する

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): ListFragment
}