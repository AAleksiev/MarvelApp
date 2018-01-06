package android.aleks.com.marvelapp.ui.comics

import android.aleks.com.marvelapp.R
import android.aleks.com.marvelapp.models.ComicItemViewModel
import android.aleks.com.marvelapp.ui.main.MainBasePresenter
import android.aleks.com.marvelapp.ui.main.MainPresenter
import android.aleks.com.marvelapp.ui.main.MainView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class MarvelComicsAdapter @Inject constructor(private val presenter: MainBasePresenter) : RecyclerView.Adapter<MarvelComicsAdapter.ComicViewHolder>() {

    //region properties
    val adapterItems: MutableList<ComicItemViewModel> = mutableListOf()
    //endregion

    //region RecyclerView.Adapter implementation
    override fun getItemCount() = adapterItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder {

        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.layout_comic_item, parent, false)
        return ComicViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {

        val viewModel = adapterItems[position]
        holder?.onBind(viewModel)
        holder?.itemView?.setOnClickListener({

            viewModel.comicId?.let { comicId ->

                presenter.getComicDetails(comicId)
            }
        })
    }
    //endregion

    //region ViewHolder class
    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //region properties
        private val comicTitleTextView: TextView = itemView.findViewById(R.id.comicTitleTextView)
        private val comicThumbnailImageView: ImageView = itemView.findViewById(R.id.comicThumbnail)
        //endregion

        fun onBind(viewModel: ComicItemViewModel) {

            bindComicTitle(viewModel)
            bindComicThumbnail(viewModel)
        }

        private fun bindComicTitle(viewModel: ComicItemViewModel){

            comicTitleTextView.text = viewModel.comicTitle
        }

        private fun bindComicThumbnail(viewModel: ComicItemViewModel) {

            Glide.with(comicThumbnailImageView)
                    .load(viewModel.comicThumbnail)
                    .error(Glide.with(comicThumbnailImageView).load(R.drawable.ic_sentiment_dissatisfied_black).transition(DrawableTransitionOptions.withCrossFade()))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(comicThumbnailImageView)
        }
    }
    //endregion
}