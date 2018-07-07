package me.jagdeep.wikisearch.inject

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.jagdeep.domain.executor.PostExecutionThread
import me.jagdeep.wikisearch.base.UiThread
import me.jagdeep.wikisearch.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}
