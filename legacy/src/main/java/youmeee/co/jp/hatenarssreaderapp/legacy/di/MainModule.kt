package youmeee.co.jp.hatenarssreaderapp.legacy.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @youmeee.co.jp.hatenarssreaderapp.legacy.di.ViewModelKey(youmeee.co.jp.hatenarssreaderapp.legacy.ui.viewmodel.MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: youmeee.co.jp.hatenarssreaderapp.legacy.ui.viewmodel.MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment.ListFragment

    @ContributesAndroidInjector
    abstract fun constributeNewListFragment(): youmeee.co.jp.hatenarssreaderapp.legacy.ui.fragment.NewListFragment
}