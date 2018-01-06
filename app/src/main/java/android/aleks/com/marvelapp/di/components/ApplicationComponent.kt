package android.aleks.com.marvelapp.di.components

import android.aleks.com.marvelapp.MarvelApp
import android.aleks.com.marvelapp.di.modules.ApplicationModule
import android.aleks.com.marvelapp.di.modules.NetworkModule
import android.aleks.com.marvelapp.rest.MarvelService
import dagger.Component
import example.aleks.com.postapp.schedulers.SchedulersProvider
import javax.inject.Singleton

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface ApplicationComponent {

    fun inject(marvelApp: MarvelApp)
    fun marvelService(): MarvelService
    fun schedulersProvider(): SchedulersProvider
}