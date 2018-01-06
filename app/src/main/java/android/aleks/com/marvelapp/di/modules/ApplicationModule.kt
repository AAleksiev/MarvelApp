package android.aleks.com.marvelapp.di.modules

import android.aleks.com.marvelapp.MarvelApp
import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.schedulers.MarvelSchedulersProvider
import example.aleks.com.postapp.schedulers.SchedulersProvider
import javax.inject.Singleton

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Module
class ApplicationModule(private val app: MarvelApp) {

    fun providesApp() = app
    @Provides
    @Singleton
    fun providesPostSchedulersProvider() = MarvelSchedulersProvider()

    @Provides
    @Singleton
    fun providesSchedulersProvider(schedulersProvider: MarvelSchedulersProvider): SchedulersProvider = schedulersProvider
}