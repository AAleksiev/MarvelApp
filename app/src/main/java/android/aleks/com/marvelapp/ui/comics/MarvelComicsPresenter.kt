package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.mvp.BasePresenter
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.utils.DiffUtilCallback
import android.support.v7.util.DiffUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
open class MarvelComicsPresenter @Inject constructor(private val marvelService: MarvelService, private val authProvider: RequestAuthProvider) : BasePresenter<MarvelComicsView> {

    //region properties
    protected var view: MarvelComicsView? = null
    private var compositeDisposable: CompositeDisposable? = null
    //endregion

    //region BasePresenter implementation
    override fun onAttach(baseView: MarvelComicsView) {

        dispose()
        compositeDisposable = CompositeDisposable()
        view = baseView
        loadComics()
    }

    override fun onDetach() {

        dispose()
        view = null
    }
    //endregion

    //load comics
    protected open fun setSchedulers(flowable: Flowable<Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>>): Flowable<Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>> {

        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getComics(): Flowable<Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>> {

        val initialPair: Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?> = (view?.viewItems ?: listOf()) to null

        return authProvider.calculateAth()
                .flatMap { mapper -> marvelService.getComics(100, mapper.ts, mapper.hash) }
                .map { map -> map.data?.results?.map { comic -> ComicItemViewModel(comic.id, comic.title, comic.thumbnail?.path) } ?: listOf() }
                .toFlowable()
                .scan(initialPair, { listDiffResultPair, currentForecastViewModel ->

                    val callback = DiffUtilCallback(listDiffResultPair.first, currentForecastViewModel)
                    val result = DiffUtil.calculateDiff(callback)
                    currentForecastViewModel to result
                })
                .filter { filter -> filter.second != null }
    }

    private fun loadComics() {

        if (view?.viewItems?.isEmpty() == true) {

            view?.showLoading()

            val disposable = setSchedulers(getComics())
                    .subscribe({ success ->

                        view?.hideLoading()
                        view?.onComicsLoaded(success.first, success.second)
                    }, { error ->

                        view?.hideLoading()
                        view?.onError(error.message)
                    })

            compositeDisposable?.add(disposable)
        }
    }
    //endregion

    //region dispose
    private fun dispose() {

        if (compositeDisposable?.isDisposed == false) {

            compositeDisposable?.dispose()
            compositeDisposable = null
        }
    }
    //endregion
}