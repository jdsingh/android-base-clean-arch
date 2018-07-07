package me.jagdeep.wikisearch.main

import me.jagdeep.domain.search.model.SearchResult

interface SearchItemListener {

    fun onSearchResultClicked(searchResult: SearchResult)

}
