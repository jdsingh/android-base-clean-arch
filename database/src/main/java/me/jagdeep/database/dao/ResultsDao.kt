package me.jagdeep.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import me.jagdeep.database.model.CachedResult

@Dao
abstract class ResultsDao {

    @Query("SELECT * FROM results where pageId IN (:pageIds)")
    abstract fun getResults(pageIds: List<Int>): Single<List<CachedResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(results: List<CachedResult>)

    @Query("DELETE from results")
    abstract fun deleteResults()

}
