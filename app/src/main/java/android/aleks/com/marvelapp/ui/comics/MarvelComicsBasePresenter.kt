package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface MarvelComicsBasePresenter : BasePresenter<MarvelComicsView> {

    fun loadComics()
}