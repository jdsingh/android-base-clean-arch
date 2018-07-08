package me.jagdeep.domain.search.model

/**
 * SearchResult domain entity.
 */
data class SearchResult(
    val pageId: Int,
    val query: String,
    val index: Int,
    val title: String,
    val description: String?,
    val imageUrl: String?
)
