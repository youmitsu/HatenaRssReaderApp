package youmeee.co.jp.hatenarssreaderapp.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import youmeee.co.jp.hatenarssreaderapp.viewModel.TopViewModel

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    internal abstract fun topViewModel(viewModel: TopViewModel): ViewModel
}
