package android.aleks.com.marvelapp.ui.main

import android.aleks.com.marvelapp.mvp.BaseView

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface MainView : BaseView {

    fun showComics()
    fun showComicDetails(comicId: Int)
}