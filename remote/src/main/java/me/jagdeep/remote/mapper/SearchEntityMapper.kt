package me.jagdeep.remote.mapper

import me.jagdeep.data.model.SearchEntity
import me.jagdeep.remote.model.Page
import javax.inject.Inject

/**
 * DataMapper for Page and SearchEntity.
 */
open class SearchEntityMapper @Inject constructor() :
    EntityMapper<Pair<String, Page>, SearchEntity> {

    override fun mapToEntity(type: Pair<String, Page>): SearchEntity {
        val (query, page) = type
        return SearchEntity(
            page.pageid,
            query,
            page.index,
            page.title,
            page.terms?.description?.firstOrNull(),
            page.thumbnail?.source
        )
    }

}
