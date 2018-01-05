package android.aleks.com.marvelapp

import android.aleks.com.marvelapp.di.components.ApplicationComponent
import android.aleks.com.marvelapp.di.components.DaggerApplicationComponent
import android.aleks.com.marvelapp.di.modules.ApplicationModule
import android.aleks.com.marvelapp.di.modules.NetworkModule
import android.app.Application
import java.io.File

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MarvelApp : Application() {

    //region properties
    val applicationComponent: ApplicationComponent by lazy {

        // Setup Dagger object graph
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(File(cacheDir, "responses")))
                .build()
    }
    //endregion

    //region Application methods
    override fun onCreate() {

        super.onCreate()
        applicationComponent.inject(this)
    }
    //endregion
}