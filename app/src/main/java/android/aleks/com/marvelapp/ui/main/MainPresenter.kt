package android.aleks.com.marvelapp.ui.main

import android.aleks.com.marvelapp.di.ActivityScope

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MainPresenter : MainBasePresenter {

    //region properties
    private var view: MainView? = null
    //endregion

    //region BasePresenter implementation
    override fun onAttach(mainView: MainView) {
        view = mainView
    }

    override fun onDetach() {
        view = null
    }
    //endregion

    //region get comic details
    override fun getComics() {

        view?.showComics()
    }

    override fun getComicDetails(comicId: Int) {

        view?.showComicDetails(comicId)
    }
    //endregion
}