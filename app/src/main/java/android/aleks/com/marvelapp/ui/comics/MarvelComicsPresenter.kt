package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.mvp.BasePresenter
import android.aleks.com.marvelapp.rest.MarvelService
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider
import android.aleks.com.marvelapp.utils.DiffUtilCallback
import android.support.v7.util.DiffUtil
import example.aleks.com.postapp.schedulers.SchedulersProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
open class MarvelComicsPresenter @Inject constructor(private val marvelService: MarvelService,
                                                     private val authProvider: RequestAuthProvider,
                                                     private val schedulersProvider: SchedulersProvider) : MarvelComicsBasePresenter {

    //region properties
    protected var view: MarvelComicsView? = null
    private var disposable: Disposable? = null
    //endregion

    //region BasePresenter implementation
    override fun onAttach(baseView: MarvelComicsView) {

        dispose()
        view = baseView
        loadComics()
    }

    override fun onDetach() {

        dispose()
        view = null
    }

    override fun loadComics() {

        if (view?.viewItems?.isEmpty() == true) {

            view?.showLoading()

            disposable = getComics()
                    .subscribeOn(schedulersProvider.ioScheduler())
                    .observeOn(schedulersProvider.mainScheduler())
                    .subscribe({ success ->

                        view?.hideLoading()
                        view?.onComicsLoaded(success.first, success.second)
                    }, { error ->

                        view?.hideLoading()
                        view?.onError(error.message)
                    })
        }
    }
    //endregion

    //load comics
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
    //endregion

    //region dispose
    private fun dispose() {

        if (disposable?.isDisposed == false) {

            disposable?.dispose()
            disposable = null
        }
    }
    //endregion
}