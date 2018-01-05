package android.aleks.com.marvelapp

import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.ui.comics.MarvelComicsPresenter
import android.support.v7.util.DiffUtil
import io.reactivex.Flowable

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MockMarvelComicsPresenter(marvelService: MarvelService, authProvider: RequestAuthProvider) : MarvelComicsPresenter(marvelService, authProvider) {

    override fun setSchedulers(flowable: Flowable<Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>>): Flowable<Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>> = flowable
}