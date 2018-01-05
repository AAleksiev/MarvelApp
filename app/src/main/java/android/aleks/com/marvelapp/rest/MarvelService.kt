package android.aleks.com.marvelapp.rest

import android.aleks.com.marvelapp.BuildConfig
import android.aleks.com.marvelapp.rest.models.ComicDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface MarvelService {

    @GET("v1/public/comics")
    fun getComics(@Query("limit") limit: Int, @Query("ts") ts: String, @Query("hash") hash: String?, @Query("apikey") apiKey: String = BuildConfig.APIID): Single<ComicDataWrapper>

    @GET("v1/public/comics/{comicId}")
    fun getComicDetails(@Path("comicId") comicId: Int?, @Query("ts") ts: String, @Query("hash") hash: String?, @Query("apikey") apiKey: String = BuildConfig.APIID): Single<ComicDataWrapper>
}