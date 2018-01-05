package android.aleks.com.marvelapp.mvp

import android.support.annotation.StringRes

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface BaseView {

    fun viewAttached()

    fun viewDetached()

    fun showLoading()

    fun hideLoading()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}