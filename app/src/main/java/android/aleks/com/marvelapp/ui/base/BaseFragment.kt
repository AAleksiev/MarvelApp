package android.aleks.com.marvelapp.ui.base

import android.aleks.com.marvelapp.di.components.ActivityComponent
import android.os.Bundle
import android.support.v4.app.Fragment
import java.lang.ClassCastException

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
abstract class BaseFragment : Fragment() {

    //region properties
    protected val activityComponent: ActivityComponent
        get() = (activity as BaseActivity).activityComponent

    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity !is BaseActivity) {

            throw ClassCastException("Hosting activity MUST inherit BaseActivity")
        }
    }
    //endregion
}
