package me.jagdeep.database.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.jagdeep.database.dao.QueriesDao
import me.jagdeep.database.dao.ResultsDao
import me.jagdeep.database.model.CachedQuery
import me.jagdeep.database.model.CachedResult
import javax.inject.Inject

@Database(entities = [CachedQuery::class, CachedResult::class], version = 1, exportSchema = true)
abstract class SearchDatabase @Inject constructor() : RoomDatabase() {

    abstract fun queriesDao(): QueriesDao

    abstract fun resultsDao(): ResultsDao

    companion object {

        private var INSTANCE: SearchDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): SearchDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            SearchDatabase::class.java, "search.db"
                        ).build()
                    }
                    return INSTANCE as SearchDatabase
                }
            }
            return INSTANCE as SearchDatabase
        }
    }

}
