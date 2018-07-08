package me.jagdeep.wikisearch.main

import me.jagdeep.domain.search.model.SearchResult

interface ItemHandler {
    fun invoke(searchResult: SearchResult)
}
