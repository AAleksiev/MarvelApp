package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.mvp.BaseView
import android.support.v7.util.DiffUtil

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface MarvelComicsView : BaseView {

    val viewItems: List<ComicItemViewModel>
    fun onComicsLoaded(pair: Pair<List<ComicItemViewModel>, DiffUtil.DiffResult?>)
}