package youmeee.co.jp.hatenarssreaderapp.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.MainActivity

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [
        AppModule::class
    ])
    abstract fun bind(): MainActivity

    //    @Module
//    class ProvideViewModel {
//
//        @Provides
//        @IntoMap
//        @ViewModelKey(TopViewModel::class)
//        fun provideTopViewModel(repository: RssRepository): ViewModel =
//                TopViewModel(repository)
//
//    }
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}