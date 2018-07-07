package me.jagdeep.domain.search.repository

import io.reactivex.Single
import me.jagdeep.domain.search.model.SearchResult

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 *
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented.
 */
interface SearchRepository {

    fun search(query: String): Single<List<SearchResult>>

}
