package dependencies

@Suppress("unused")
object Deps {

    object GradlePlugin {
        val android = "com.android.tools.build:gradle:3.4.1"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.Kotlin.version}"
        val google_service = "com.google.gms:google-services:4.2.0"
        val fabric = "io.fabric.tools:gradle:1.29.0"
        val firebase_performance = "com.google.firebase:perf-plugin:1.2.1"
        val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Deps.AndroidX.Navigation.version}"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:1.1.0-beta01"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta1"
        val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
        val design = "com.google.android.material:material:1.0.0"
        val cardView = "androidx.cardview:cardview:1.0.0"
        val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-alpha06"
        val coreArch = "androidx.arch.core:core-runtime:2.1.0-beta01"

        object LifeCycle {
            val version = "2.2.0-alpha01"
            val extension = "androidx.lifecycle:lifecycle-extensions:$version"
            val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Navigation {
            val version = "2.1.0-alpha05"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.0.0"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:2.0.0"
        }
    }

    object Test {
        val junit = "junit:junit:4.12"
        val testRunner = "com.android.support.test:runner:1.0.1"

        object Espresso {
            val core = "com.android.support.test.espresso:espresso-core:3.0.1"
        }
    }

    object Kotlin {
        val version = "1.3.10"
        val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"

        object Coroutines {
            val version = "1.0.1"
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Dagger {
        val version = "2.20"
        val dagger = "com.google.dagger:dagger:$version"
        val android = "com.google.dagger:dagger-android:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val compiler = "com.google.dagger:dagger-compiler:$version"
        val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Firebase {
        val core = "com.google.firebase:firebase-core:16.0.9"
        val remoteConfig = "com.google.firebase:firebase-config:18.0.0"
        val messaging = "com.google.firebase:firebase-messaging:19.0.0"
        val inAppMessaging = "com.google.firebase:firebase-inappmessaging-display:17.2.0"
        val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        val perf = "com.google.firebase:firebase-perf:18.0.0"
    }

    object Retrofit {
        val version = "2.5.0"
        val retrofit = "com.squareup.retrofit2:retrofit:$version"
        val coroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        val xmlConverter = "com.squareup.retrofit2:converter-simplexml:$version"
        val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
    }

    object OkHttp {
        val version = "3.10.0"
        val okhttp = "com.squareup.okhttp3:okhttp:$version"
        val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Glide {
        val version = "4.8.0"
        val glide = "com.github.bumptech.glide:glide:$version"
        val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object ThreetenAbp {
        val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.1.1"
    }

}