package youmeee.co.jp.hatenarssreaderapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import youmeee.co.jp.hatenarssreaderapp.di.AppModule
import youmeee.co.jp.hatenarssreaderapp.di.DaggerAppComponent

/**
 * App
 */
open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}