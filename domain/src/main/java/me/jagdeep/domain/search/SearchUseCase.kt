package me.jagdeep.domain.search

import io.reactivex.Observable
import me.jagdeep.domain.executor.PostExecutionThread
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.domain.search.repository.SearchRepository
import me.jagdeep.domain.usecases.ObservableUseCase
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val searchRepository: SearchRepository
) : ObservableUseCase<List<SearchResult>, SearchUseCase.Companion.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params): Observable<List<SearchResult>> {
        return searchRepository.search(params.query).toObservable()
    }

    companion object {
        data class Params(val query: String)
    }

}
