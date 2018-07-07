package me.jagdeep.domain.search.model

/**
 * SearchResult domain entity.
 */
data class SearchResult(
    val pageId: Int,
    val index: Int,
    val title: String,
    val description: String?,
    val imageUrl: String?
)
