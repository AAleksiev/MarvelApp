package android.aleks.com.marvelapp.ui.base

import android.aleks.com.marvelapp.MarvelApp
import android.aleks.com.marvelapp.R
import android.aleks.com.marvelapp.di.components.ActivityComponent
import android.aleks.com.marvelapp.di.components.DaggerActivityComponent
import android.aleks.com.marvelapp.di.modules.ActivityModule
import android.aleks.com.marvelapp.mvp.BaseView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
open class BaseActivity : AppCompatActivity(), BaseView {

    //region properties
    val activityComponent: ActivityComponent by lazy {

        // Setup Dagger object graph
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule())
                .applicationComponent((application as MarvelApp).applicationComponent)
                .build()
    }
    //endregion

    //region AppCompatActivity implementation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {

            android.R.id.home -> {

                if (supportFragmentManager.backStackEntryCount > 0) {

                    supportFragmentManager.popBackStackImmediate()

                    return true
                }
            }
            else -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region show screen
    fun showScreen(content: Fragment,
                   contentTag: String,
                   addToBackStack: Boolean,
                   transitionContent: Boolean) {

        val ft = supportFragmentManager.beginTransaction()

        // Content area slide animation
        if (transitionContent) {

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }

        ft.replace(R.id.placeholder_content, content, contentTag)

        if (addToBackStack) {
            ft.addToBackStack(contentTag + System.identityHashCode(content).toString())
        }

        ft.commitAllowingStateLoss()
    }
    //endregion

    override fun viewAttached() {

    }

    override fun viewDetached() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(resId: Int) {

    }

    override fun onError(message: String?) {

    }

    override fun showMessage(message: String?) {

    }

    override fun showMessage(resId: Int) {

    }

    override fun isNetworkConnected() = true

    override fun hideKeyboard() {

    }
}