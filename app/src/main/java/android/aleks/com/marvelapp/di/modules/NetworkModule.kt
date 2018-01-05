package android.aleks.com.marvelapp.di.modules

import android.aleks.com.marvelapp.BuildConfig
import android.aleks.com.marvelapp.rest.MarvelService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
@Module
class NetworkModule(private var cacheFile: File) {

    @Provides
    @Singleton
    internal fun providesRetrofit(): Retrofit {

        var cache: Cache? = null

        try {

            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    // Customize the request
                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                            .build()

                    val response = chain.proceed(request)
                    response.cacheResponse()
                    // Customize or return the response
                    response
                }
                .cache(cache)
                .build()


        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesMarvelService(retrofit: Retrofit) = retrofit.create(MarvelService::class.java)
}