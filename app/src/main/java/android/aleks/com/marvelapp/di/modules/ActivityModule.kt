package android.aleks.com.marvelapp.di.modules

import android.aleks.com.marvelapp.di.ActivityScope
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.ui.comics.MarvelComicsAdapter
import android.aleks.com.marvelapp.ui.comics.MarvelComicsBasePresenter
import android.aleks.com.marvelapp.ui.comics.MarvelComicsPresenter
import android.aleks.com.marvelapp.ui.details.ComicDetailsBasePresenter
import android.aleks.com.marvelapp.ui.details.ComicDetailsPresenter
import android.aleks.com.marvelapp.ui.main.MainBasePresenter
import android.aleks.com.marvelapp.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.schedulers.SchedulersProvider

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun providesMainPresenter() = MainPresenter()

    @Provides
    @ActivityScope
    fun providesMainBasePresenter(mainPresenter: MainPresenter): MainBasePresenter = mainPresenter

    @Provides
    fun providesMarvelComicsPresenter(marvelService: MarvelService, requestAuthProvider: RequestAuthProvider, schedulersProvider: SchedulersProvider) = MarvelComicsPresenter(marvelService, requestAuthProvider, schedulersProvider)

    @Provides
    fun providesMarvelComicsBasePresenter(marvelComicsPresenter: MarvelComicsPresenter): MarvelComicsBasePresenter = marvelComicsPresenter

    @Provides
    fun providesComicDetailsPresenter(marvelService: MarvelService, requestAuthProvider: RequestAuthProvider, schedulersProvider: SchedulersProvider) = ComicDetailsPresenter(marvelService, requestAuthProvider, schedulersProvider)

    @Provides
    fun providesComicDetailsBasePresenter(comicDetailsPresenter: ComicDetailsPresenter): ComicDetailsBasePresenter = comicDetailsPresenter

    @Provides
    fun providesAuthProvider() = RequestAuthProvider()

    @Provides
    fun providesMarvelComicsAdapter(mainPresenter: MainBasePresenter) = MarvelComicsAdapter(mainPresenter)
}