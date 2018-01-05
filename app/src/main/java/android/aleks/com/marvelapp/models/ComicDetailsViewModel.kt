package android.aleks.com.marvelapp.models

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
data class ComicDetailsViewModel(val comicId: Int?,
                                 val comicTitle: String?,
                                 val comicThumbnail: String?,
                                 val comicDescription: String?,
                                 val authors: List<AuthorViewModel>?)