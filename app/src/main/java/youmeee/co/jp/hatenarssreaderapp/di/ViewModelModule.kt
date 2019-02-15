package youmeee.co.jp.hatenarssreaderapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
abstract class ViewModelModule<VM : ViewModel>(
        private val kClass: Class<VM>
) {
    @Provides
    fun provideViewModel(
            activity: AppCompatActivity,
            factory: ViewModelInjectorFactory<VM>
    ): VM {
        return ViewModelProviders.of(activity, factory).get(kClass)
    }
}

class ViewModelInjectorFactory<T : ViewModel> @Inject constructor(
        private val injector: MembersInjector<T>
) : ViewModelProvider.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return modelClass.newInstance().apply { injector.injectMembers(this as T) }
    }
}