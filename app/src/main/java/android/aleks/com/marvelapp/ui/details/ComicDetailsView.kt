package android.aleks.com.marvelapp.ui.details

import android.aleks.com.marvelapp.models.ComicDetailsViewModel
import android.aleks.com.marvelapp.mvp.BaseView

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface ComicDetailsView : BaseView {

    fun onComicLoaded(comicDetailsViewModel: ComicDetailsViewModel?)
}