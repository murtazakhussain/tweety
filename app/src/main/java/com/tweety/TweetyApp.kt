package com.tweety

import android.app.Application

import com.tweety.dependencies.AppComponent
import com.tweety.dependencies.AppModule
import com.tweety.dependencies.DaggerAppComponent
import com.tweety.networking.NetworkModule

import java.io.File

class TweetyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val cacheFile = File(cacheDir, "responses")
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(cacheFile))
                .build()

        appComponent.inject(this)
    }

}
