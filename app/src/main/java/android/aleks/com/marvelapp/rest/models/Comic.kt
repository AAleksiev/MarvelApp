package android.aleks.com.marvelapp.rest.models

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
data class Comic(val id: Int?, val title: String?, val description: String?, val pageCount: Int?,
                 val thumbnail: Image?, val images: List<Image>?,
                 val creators: CreatorList?)