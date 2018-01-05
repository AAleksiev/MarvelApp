package android.aleks.com.marvelapp.di.modules

import android.aleks.com.marvelapp.di.ActivityScope
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.ui.comics.MarvelComicsPresenter
import android.aleks.com.marvelapp.ui.comics.MarvelComicsAdapter
import android.aleks.com.marvelapp.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun providesMainPresenter() = MainPresenter()

    @Provides
    fun providesMarvelComicsPresenter(marvelService: MarvelService, requestAuthProvider: RequestAuthProvider): MarvelComicsPresenter = MarvelComicsPresenter(marvelService, requestAuthProvider)

    @Provides
    fun providesAuthProvider() = RequestAuthProvider()

    @Provides
    fun providesMarvelComicsAdapter(mainPresenter: MainPresenter) = MarvelComicsAdapter(mainPresenter)
}