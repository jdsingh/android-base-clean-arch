package me.jagdeep.database.mapper

import me.jagdeep.data.model.SearchEntity
import me.jagdeep.database.model.CachedResult
import javax.inject.Inject

class CacheResultMapper @Inject constructor() :
    CacheMapper<SearchEntity, Pair<String, CachedResult>> {

    override fun mapToCache(type: SearchEntity): Pair<String, CachedResult> {
        val result = CachedResult(
            type.pageId,
            type.title,
            type.description,
            type.imageUrl
        )
        return type.query to result
    }

    override fun mapFromCache(type: Pair<String, CachedResult>): SearchEntity {
        val (query, item) = type
        return SearchEntity(
            item.pageId,
            query,
            0,
            item.title,
            item.description,
            item.imageUrl
        )
    }

}
