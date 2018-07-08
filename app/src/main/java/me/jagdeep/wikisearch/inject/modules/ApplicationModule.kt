package me.jagdeep.wikisearch.inject.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindApplicationContext(application: Application): Context

}
