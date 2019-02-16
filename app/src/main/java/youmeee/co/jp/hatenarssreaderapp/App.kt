package youmeee.co.jp.hatenarssreaderapp

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DaggerApplication
import youmeee.co.jp.hatenarssreaderapp.di.DaggerAppComponent

/**
 * App
 */
open class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}