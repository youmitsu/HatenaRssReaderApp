package youmeee.co.jp.hatenarssreaderapp.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.MainActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.TopViewModel

@Module
class MainActivityModule {
    @Provides
    fun provideTopViewModel(activity: MainActivity): TopViewModel {
        return ViewModelProviders.of(activity).get(TopViewModel::class.java)
    }
}