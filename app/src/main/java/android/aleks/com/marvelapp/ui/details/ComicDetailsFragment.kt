package android.aleks.com.marvelapp.ui.details

import android.aleks.com.marvelapp.R
import android.aleks.com.marvelapp.models.ComicDetailsViewModel
import android.aleks.com.marvelapp.ui.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class ComicDetailsFragment : BaseFragment(), ComicDetailsView {

    //region properties
    @Inject
    lateinit var presenter: ComicDetailsBasePresenter

    private var comicId: Int = 0

    private lateinit var loadingView: ProgressBar
    private lateinit var comicTitleTextView: TextView
    private lateinit var comicDescriptionTextView: TextView
    private lateinit var comicThumbnailImageView: ImageView
    //endregion

    //region companion object
    companion object {

        val TAG = ComicDetailsFragment::class.java.simpleName
        private val comicIdKey = "ComicDetailsFragment.comicId"

        fun newInstance(comicId: Int): ComicDetailsFragment {

            val bundle = Bundle()
            bundle.putInt(comicIdKey, comicId)

            val comicDetailsFragment = ComicDetailsFragment()
            comicDetailsFragment.arguments = bundle

            return comicDetailsFragment
        }
    }
    //endregion


    //region Fragment implementation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        val bundle = savedInstanceState ?: arguments
        comicId = bundle.getInt(comicIdKey, -1)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_comic_details, container, false)

        loadingView = rootView.findViewById(R.id.loadingIndicator)
        comicTitleTextView = rootView.findViewById(R.id.comicTitleTextView)
        comicDescriptionTextView = rootView.findViewById(R.id.comicDescriptionTextView)
        comicThumbnailImageView = rootView.findViewById(R.id.comicThumbnail)

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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(comicIdKey, comicId)
    }
    //endregion

    //region BaseView implementation
    override fun viewAttached() {

        presenter.onAttach(this)
        presenter.loadComicDetails(comicId)
    }

    override fun viewDetached() {

        presenter.onDetach()
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

    override fun onComicLoaded(comicDetailsViewModel: ComicDetailsViewModel?) {

        comicDetailsViewModel?.let {

            bind(it)
        }

    }
    //endregion

    //region bind data
    private fun bind(viewModel: ComicDetailsViewModel){

        comicTitleTextView.text = viewModel.comicTitle ?: getString(R.string.not_available)
        comicDescriptionTextView.text = viewModel.comicDescription ?: getString(R.string.not_available)

        Glide.with(comicThumbnailImageView)
                .load(viewModel.comicThumbnail)
                .error(Glide.with(comicThumbnailImageView).load(R.drawable.ic_sentiment_dissatisfied_black).transition(DrawableTransitionOptions.withCrossFade()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(comicThumbnailImageView)
    }
    //endregion

}// Required empty public constructor