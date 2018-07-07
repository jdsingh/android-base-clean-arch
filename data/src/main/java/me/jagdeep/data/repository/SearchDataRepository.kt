package me.jagdeep.data.repository

import io.reactivex.Single
import me.jagdeep.data.mapper.SearchMapper
import me.jagdeep.data.source.SearchRemote
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.domain.search.repository.SearchRepository
import javax.inject.Inject

/**
 * Implementation of [SearchRepository].
 */
class SearchDataRepository @Inject constructor(
    private val mapper: SearchMapper,
    private val remote: SearchRemote
) : SearchRepository {

    override fun search(query: String): Single<List<SearchResult>> {
        return remote.search(query)
            .map { entities ->
                entities.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

}
