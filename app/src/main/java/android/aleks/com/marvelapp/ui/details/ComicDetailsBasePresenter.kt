package android.aleks.com.marvelapp.ui.details

import android.aleks.com.marvelapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface ComicDetailsBasePresenter : BasePresenter<ComicDetailsView> {

    fun loadComicDetails(comicId: Int?)
}