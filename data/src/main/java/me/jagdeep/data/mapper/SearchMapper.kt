package me.jagdeep.data.mapper

import me.jagdeep.data.model.SearchEntity
import me.jagdeep.domain.search.model.SearchResult
import javax.inject.Inject

open class SearchMapper @Inject constructor() : DataMapper<SearchEntity, SearchResult> {

    override fun mapToEntity(type: SearchResult): SearchEntity {
        return SearchEntity(
            type.pageId,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

    override fun mapFromEntity(type: SearchEntity): SearchResult {
        return SearchResult(
            type.pageId,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

}
