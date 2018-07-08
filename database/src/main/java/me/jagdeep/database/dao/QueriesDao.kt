package me.jagdeep.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import me.jagdeep.database.model.CachedQuery

@Dao
abstract class QueriesDao {

    @Query("SELECT * FROM queries WHERE text = :query")
    abstract fun getQuery(query: String): Single<List<CachedQuery>>

    @Query("SELECT COUNT(id) FROM queries WHERE text = :query")
    abstract fun isQueryCached(query: String): Single<Int>

    @Insert
    abstract fun insertQuery(query: CachedQuery)

}
