package me.jagdeep.wikisearch.inject.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.jagdeep.data.source.SearchCache
import me.jagdeep.database.SearchCacheImpl
import me.jagdeep.database.db.SearchDatabase

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): SearchDatabase {
            return SearchDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindSearchCache(searchDatabase: SearchCacheImpl): SearchCache

}
