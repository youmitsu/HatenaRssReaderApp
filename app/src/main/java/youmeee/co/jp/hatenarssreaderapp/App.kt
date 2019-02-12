package youmeee.co.jp.hatenarssreaderapp

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import youmeee.co.jp.hatenarssreaderapp.di.DaggerAppComponent
import javax.inject.Inject

/**
 * App
 */
open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val component = DaggerAppComponent.builder()
                .create(this)
        component.inject(this)
        AndroidThreeTen.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}