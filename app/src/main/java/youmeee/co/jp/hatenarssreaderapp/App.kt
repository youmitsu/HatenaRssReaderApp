package youmeee.co.jp.hatenarssreaderapp

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DaggerApplication
import youmeee.co.jp.hatenarssreaderapp.di.DaggerAppComponent
import youmeee.co.jp.hatenarssreaderapp.di.applyAutoInjector

/**
 * App
 */
open class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
        AndroidThreeTen.init(this)
    }
}