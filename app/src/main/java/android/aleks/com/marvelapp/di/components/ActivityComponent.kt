package android.aleks.com.marvelapp.di.components

import android.aleks.com.marvelapp.ui.main.MainActivity
import android.aleks.com.marvelapp.di.ActivityScope
import android.aleks.com.marvelapp.di.modules.ActivityModule
import android.aleks.com.marvelapp.ui.comics.MarvelComicsFragment
import android.aleks.com.marvelapp.ui.details.ComicDetailsFragment
import dagger.Component

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: ComicDetailsFragment)
    fun inject(marvelComicsFragment: MarvelComicsFragment)
}