package me.jagdeep.data.source

import io.reactivex.Completable
import io.reactivex.Single
import me.jagdeep.data.model.SearchEntity

/**
 * Interface defining methods for the cache service.
 *
 * This is to be implemented by the database layer, using this interface as a way of communicating.
 */
interface SearchCache {

    fun search(query: String): Single<List<SearchEntity>>

    fun isQueryCached(query: String): Single<Boolean>

    fun cacheSearch(query: String, result: List<SearchEntity>): Completable

}
