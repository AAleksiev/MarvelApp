package android.aleks.com.marvelapp.ui.details

import android.aleks.com.marvelapp.models.AuthorViewModel
import android.aleks.com.marvelapp.models.ComicDetailsViewModel
import android.aleks.com.marvelapp.mvp.BasePresenter
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class ComicDetailsPresenter @Inject constructor(private val marvelService: MarvelService, private val authProvider: RequestAuthProvider) : BasePresenter<ComicDetailsView> {

    //region properties
    private var view: ComicDetailsView? = null
    private var disposable: Disposable? = null
    //endregion

    //region BasePresenter implementation
    override fun onAttach(baseView: ComicDetailsView) {

        dispose()
        view = baseView
    }

    override fun onDetach() {

        dispose()
        view = null
    }
    //endregion

    //region load comic details
    fun loadComicDetails(comicId: Int?) {

        view?.showLoading()

        authProvider.calculateAth()
                .subscribeOn(Schedulers.io())
                .flatMap { mapper -> marvelService.getComicDetails(comicId, mapper.ts, mapper.hash) }
                .map { map -> map.data?.results?.map { comic -> ComicDetailsViewModel(comic.id, comic.title, comic.thumbnail?.path, comic.description, comic.creators?.items?.map { m -> AuthorViewModel(m?.name, m?.role) }) } ?: listOf() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success ->

                    view?.hideLoading()
                    view?.onComicLoaded(success.firstOrNull())
                }, { error ->

                    view?.hideLoading()
                    view?.onError(error.message)
                })
    }
    //endregion

    //region dispose
    fun dispose() {

        if (disposable?.isDisposed == false) {

            disposable?.dispose()
            disposable = null
        }
    }
    //endregion
}