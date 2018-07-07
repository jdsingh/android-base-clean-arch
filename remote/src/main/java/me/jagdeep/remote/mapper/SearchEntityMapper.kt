package me.jagdeep.remote.mapper

import me.jagdeep.data.model.SearchEntity
import me.jagdeep.remote.model.Page
import javax.inject.Inject

/**
 * DataMapper for Page and SearchEntity.
 */
open class SearchEntityMapper @Inject constructor() : EntityMapper<Page, SearchEntity> {

    override fun mapToEntity(type: Page): SearchEntity {
        return SearchEntity(
            type.pageid,
            type.index,
            type.title,
            type.terms?.description?.firstOrNull(),
            type.thumbnail?.source
        )
    }

}
