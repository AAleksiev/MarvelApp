package android.aleks.com.marvelapp.rest.auth

import android.aleks.com.marvelapp.BuildConfig
import android.aleks.com.marvelapp.utils.Utils
import io.reactivex.Single
import java.lang.Exception
import java.util.*

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
open class RequestAuthProvider {

    fun calculateAth(): Single<AuthModel> {

        return Single.create({ source ->

            try {

                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                val ts = (calendar.timeInMillis / 1000L).toString()
                val hash = Utils.md5(ts + BuildConfig.SIGNATURE + BuildConfig.APIID)

                source.onSuccess(AuthModel(ts, hash))
            } catch (exception: Exception) {

                source.onError(exception)
            }
        })
    }
}