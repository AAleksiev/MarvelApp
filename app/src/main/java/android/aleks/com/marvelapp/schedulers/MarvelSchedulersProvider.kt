package example.aleks.com.postapp.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by aleks on 06/01/2018.
 */
class MarvelSchedulersProvider : SchedulersProvider {

    override fun ioScheduler() = Schedulers.io()

    override fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun computationScheduler() = Schedulers.computation()
}