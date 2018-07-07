package me.jagdeep.remote

import io.reactivex.Single
import me.jagdeep.data.model.SearchEntity
import me.jagdeep.data.source.SearchRemote
import me.jagdeep.remote.mapper.SearchEntityMapper
import javax.inject.Inject

/**
 * Implementation of [SearchRemote].
 */
class SearchRemoteImpl @Inject constructor(
    private val mapper: SearchEntityMapper,
    private val service: ApiService
) : SearchRemote {

    override fun search(query: String): Single<List<SearchEntity>> {
        return service.search(query)
            .map { response ->
                response.query.pages
                    .sortedBy { it.index }
                    .map { page -> mapper.mapToEntity(page) }
            }
    }

}
