package example.aleks.com.postapp.schedulers

import io.reactivex.Scheduler

/**
 * Created by aleks on 06/01/2018.
 */
interface SchedulersProvider {

    fun ioScheduler(): Scheduler
    fun mainScheduler(): Scheduler
    fun computationScheduler(): Scheduler
}