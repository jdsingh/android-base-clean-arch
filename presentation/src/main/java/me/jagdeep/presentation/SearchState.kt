package me.jagdeep.presentation

import me.jagdeep.domain.search.model.SearchResult

sealed class SearchState {

    class Error(val message: String) : SearchState()

    class Success(val result: List<SearchResult>) : SearchState()

}
