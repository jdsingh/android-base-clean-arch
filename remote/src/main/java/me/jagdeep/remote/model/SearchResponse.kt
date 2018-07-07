package me.jagdeep.remote.model

data class SearchResponse(
    val query: Query
)

data class Query(
    val pages: List<Page>
)

data class Page(
    val pageid: Int,
    val index: Int,
    val title: String,
    val thumbnail: Thumbnail?,
    val terms: Terms?
)

data class Thumbnail(
    val source: String
)

data class Terms(
    val description: List<String>
)

