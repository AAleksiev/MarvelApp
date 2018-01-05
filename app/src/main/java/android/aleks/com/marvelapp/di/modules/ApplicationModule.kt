package android.aleks.com.marvelapp.di.modules

import android.aleks.com.marvelapp.MarvelApp
import dagger.Module

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Module
class ApplicationModule(private val app: MarvelApp) {

    fun providesApp() = app
}