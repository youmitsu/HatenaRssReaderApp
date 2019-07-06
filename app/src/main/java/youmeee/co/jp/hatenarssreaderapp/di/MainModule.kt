package youmeee.co.jp.hatenarssreaderapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import youmeee.co.jp.hatenarssreaderapp.presentation.fragment.NewListFragment
import youmeee.co.jp.hatenarssreaderapp.presentation.fragment.ListFragment
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.MainViewModel

@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun constributeNewListFragment(): NewListFragment
}