package youmeee.co.jp.hatenarssreaderapp.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import youmeee.co.jp.hatenarssreaderapp.presentation.activity.MainActivity
import youmeee.co.jp.hatenarssreaderapp.presentation.viewmodel.MainViewModel

@Module
interface MainActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        MainViewModelModule::class
    ])
    fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityModule {
    @Binds
    fun provideAppCompatActivity(mainActivity: MainActivity): AppCompatActivity
}

@Module
class MainViewModelModule : ViewModelModule<MainViewModel>(MainViewModel::class.java)
