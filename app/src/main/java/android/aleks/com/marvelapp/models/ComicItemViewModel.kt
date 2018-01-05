package android.aleks.com.marvelapp.models

import java.util.*

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
data class ComicItemViewModel(val comicId: Int?, val comicTitle: String?, val comicThumbnail: String?) : UniqueId {

    override val uniqueId: String
        get() = comicId?.toString() ?: UUID.randomUUID().toString()
}