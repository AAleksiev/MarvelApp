package android.aleks.com.marvelapp.ui.main

import android.aleks.com.marvelapp.mvp.BasePresenter

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MainPresenter  : BasePresenter<MainView> {

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
    fun getComicDetails(comicId: Int) {

        view?.showComicDetails(comicId)
    }
    //endregion
}