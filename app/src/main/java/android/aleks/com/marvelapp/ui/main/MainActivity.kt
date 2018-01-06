package android.aleks.com.marvelapp.ui.main

import android.aleks.com.marvelapp.R
import android.aleks.com.marvelapp.ui.base.BaseActivity
import android.aleks.com.marvelapp.ui.comics.MarvelComicsFragment
import android.aleks.com.marvelapp.ui.details.ComicDetailsFragment
import android.os.Bundle
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    //region properties
    @Inject
    lateinit var presenter: MainBasePresenter
    //endregion

    //region activity methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)

        presenter.onAttach(this)

        if (savedInstanceState == null) {

            presenter.getComics()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
    //endregion

    //region MainView implementation
    override fun showComics() {

        var fragment = supportFragmentManager.findFragmentByTag(MarvelComicsFragment.TAG)
        if (fragment == null) {

            fragment = MarvelComicsFragment.newInstance()
        }

        showScreen(fragment, MarvelComicsFragment.TAG, false, true)
    }

    override fun showComicDetails(comicId: Int) {

        var fragment = supportFragmentManager.findFragmentByTag(ComicDetailsFragment.TAG)
        if (fragment == null) {

            fragment = ComicDetailsFragment.newInstance(comicId)
        }

        showScreen(fragment, ComicDetailsFragment.TAG, true, true)
    }
    //endregion
}
