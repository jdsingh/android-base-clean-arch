package me.jagdeep.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import me.jagdeep.domain.search.SearchUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val searchState = MutableLiveData<SearchState>()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        publishSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .filter { it.isNotBlank() }
            .distinctUntilChanged()
            .switchMap { query ->
                searchUseCase.execute(SearchUseCase.Companion.Params(query)).toObservable()
            }
            .onErrorResumeNext { e: Throwable ->
                searchState.value = SearchState.Error(e.localizedMessage)
                return@onErrorResumeNext Observable.just(emptyList())
            }
            .subscribe { results ->
                searchState.value = SearchState.Success(results)
            }
            .addTo(searchUseCase.disposables)
    }

    fun search(query: String) {
        publishSubject.onNext(query)
    }

    fun searchResult(): LiveData<SearchState> = searchState

    override fun onCleared() {
        searchUseCase.dispose()
    }

}
