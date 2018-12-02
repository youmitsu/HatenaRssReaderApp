package youmeee.co.jp.hatenarssreaderapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.MainActivity

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Module
abstract class ActivityModule {

    // @ContributesAndroidInjectorでAndroidInjection.inject(this)を可能にする
    // 戻り値の型が対象となるクラス

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}