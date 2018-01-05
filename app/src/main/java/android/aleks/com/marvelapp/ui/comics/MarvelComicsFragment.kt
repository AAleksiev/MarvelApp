package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.R
import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.ui.base.BaseFragment
import android.aleks.com.marvelapp.utils.RecyclerViewItemsSpaceDecoration
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import javax.inject.Inject

class MarvelComicsFragment : BaseFragment(), MarvelComicsView {

    //region properties
    @Inject
    lateinit var marvelComicsPresenter: MarvelComicsPresenter

    @Inject
    lateinit var marvelComicsAdapter: MarvelComicsAdapter

    private lateinit var marvelComicsRecyclerView: RecyclerView
    private lateinit var loadingView: ProgressBar

    override val viewItems: List<ComicItemViewModel>
        get() = marvelComicsAdapter.adapterItems
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent.inject(this)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_marvel_comics, container, false)

        loadingView = rootView.findViewById(R.id.loadingIndicator)
        marvelComicsRecyclerView = rootView.findViewById(R.id.comicsRecyclerView)

        val itemMargin = resources.getDimension(R.dimen.dimen_5dp).toInt()
        marvelComicsRecyclerView.layoutManager = LinearLayoutManager(context)
        marvelComicsRecyclerView.addItemDecoration(RecyclerViewItemsSpaceDecoration(0, itemMargin, 0, itemMargin))
        marvelComicsRecyclerView.adapter = marvelComicsAdapter

        return rootView
    }

    override fun onResume() {
        super.onResume()
        viewAttached()
    }

    override fun onPause() {
        super.onPause()
        viewDetached()
    }
    //endregion

    //region companion object
    companion object {

        val TAG = MarvelComicsFragment::class.java.simpleName
        fun newInstance(): MarvelComicsFragment = MarvelComicsFragment()
    }
    //endregion

    //region MainView implementation
    override fun viewAttached() {

        marvelComicsPresenter.onAttach(this)
    }

    override fun viewDetached() {

        marvelComicsPresenter.onDetach()
    }

    override fun showLoading() {

        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        loadingView.visibility = View.GONE
    }

    override fun onError(resId: Int) {

    }

    override fun onError(message: String?) {

    }

    override fun showMessage(message: String?) {

    }

    override fun showMessage(resId: Int) {

    }

    override fun isNetworkConnected(): Boolean = true

    override fun hideKeyboard() {

    }

    override fun onComicsLoaded(comics: List<ComicItemViewModel>, diffResult: DiffUtil.DiffResult?) {

        marvelComicsAdapter.adapterItems.clear()
        marvelComicsAdapter.adapterItems.addAll(comics)
        diffResult?.dispatchUpdatesTo(marvelComicsAdapter)
    }
    //endregion

}// Required empty public constructor
