package youmeee.co.jp.hatenarssreaderapp.legacy.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.legacy.ui.activity.MainActivity

@Module
abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: youmeee.co.jp.hatenarssreaderapp.legacy.di.ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}