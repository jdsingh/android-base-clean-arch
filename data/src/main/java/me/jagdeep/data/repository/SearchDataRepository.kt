package me.jagdeep.data.repository

import io.reactivex.Single
import me.jagdeep.data.mapper.SearchMapper
import me.jagdeep.data.source.SearchCache
import me.jagdeep.data.source.SearchRemote
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.domain.search.repository.SearchRepository
import javax.inject.Inject

/**
 * Implementation of [SearchRepository].
 */
class SearchDataRepository @Inject constructor(
    private val mapper: SearchMapper,
    private val remote: SearchRemote,
    private val cache: SearchCache
) : SearchRepository {

    override fun search(query: String): Single<List<SearchResult>> {
        return cache.isQueryCached(query)
            .flatMap { isCached ->
                if (isCached) {
                    cache.search(query)
                } else {
                    remote.search(query)
                        .doAfterSuccess { results ->
                            cache.cacheSearch(query, results)
                                .subscribe({
                                    // query cached
                                }, {
                                    // cache failed
                                })
                        }
                }
            }
            .map { entities ->
                entities.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

}
