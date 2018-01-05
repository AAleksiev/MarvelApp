package android.aleks.com.marvelapp.mock

import android.aleks.com.marvelapp.models.ComicDetailsViewModel
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.ui.details.ComicDetailsPresenter
import io.reactivex.Single

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MockComicDetailsPresenter(marvelService: MarvelService, authProvider: RequestAuthProvider) : ComicDetailsPresenter(marvelService, authProvider) {

    override fun setSchedulers(single: Single<List<ComicDetailsViewModel>>): Single<List<ComicDetailsViewModel>> = single
}