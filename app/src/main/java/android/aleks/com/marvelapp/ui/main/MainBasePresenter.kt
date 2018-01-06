package android.aleks.com.marvelapp.ui.main

import android.aleks.com.marvelapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface MainBasePresenter : BasePresenter<MainView> {

    fun getComics()
    fun getComicDetails(comicId: Int)
}