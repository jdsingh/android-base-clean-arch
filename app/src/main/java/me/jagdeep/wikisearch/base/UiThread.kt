package me.jagdeep.wikisearch.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jagdeep.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()

}
