package android.aleks.com.marvelapp.mvp

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
interface BasePresenter<in V : BaseView> {

    fun onAttach(baseView: V)

    fun onDetach()
}