package me.jagdeep.data.source

import io.reactivex.Single
import me.jagdeep.data.model.SearchEntity

/**
 * Interface defining methods for the remote service.
 *
 * This is to be implemented by the remote layer, using this interface as a way of communicating.
 */
interface SearchRemote {

    fun search(query: String): Single<List<SearchEntity>>

}
