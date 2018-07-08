package me.jagdeep.domain.search

import io.reactivex.Single
import me.jagdeep.domain.executor.PostExecutionThread
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.domain.search.repository.SearchRepository
import me.jagdeep.domain.usecases.SingleUseCase
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val searchRepository: SearchRepository
) : SingleUseCase<List<SearchResult>, SearchUseCase.Companion.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params): Single<List<SearchResult>> {
        return searchRepository.search(params.query)
    }

    companion object {
        data class Params(val query: String)
    }

}
