package me.jagdeep.remote

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import me.jagdeep.data.model.SearchEntity
import me.jagdeep.remote.mapper.SearchEntityMapper
import me.jagdeep.remote.model.Page
import me.jagdeep.remote.model.SearchResponse
import me.jagdeep.remote.test.RemoteDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
open class SearchRemoteImplTest {

    private val mapper: SearchEntityMapper = mock()
    private val service: ApiService = mock()

    private val searchRemote = SearchRemoteImpl(mapper, service)

    @Test
    fun `search should completes`() {
        stubApiServiceSearch(Single.just(RemoteDataFactory.createSearchResponse()))

        val testObserver = searchRemote.search("Jagdeep").test()
        testObserver.assertComplete()
    }

    @Test
    fun `search should call server`() {
        stubApiServiceSearch(Single.just(RemoteDataFactory.createSearchResponse()))

        searchRemote.search("Jagdeep").test()
        verify(service).search(any())
    }

    @Test
    fun `search should call server with correct arguments`() {
        stubApiServiceSearch(Single.just(RemoteDataFactory.createSearchResponse()))

        searchRemote.search("Jagdeep").test()
        verify(service).search(eq("Jagdeep"))
    }

    @Test
    fun `search should return response`() {
        val response = RemoteDataFactory.createSearchResponse()
        stubApiServiceSearch(Single.just(response))
        val entities = mutableListOf<SearchEntity>()
        response.query.pages.forEach {
            val entity = RemoteDataFactory.createSearchEntity()
            entities.add(entity)
            stubMapperMapToEntity(it, entity)
        }

        val testObserver = searchRemote.search("Jagdeep").test()
        testObserver
            .assertValue(entities)
    }

    private fun stubApiServiceSearch(response: Single<SearchResponse>) {
        whenever(service.search(eq("Jagdeep")))
            .thenReturn(response)
    }

    private fun stubMapperMapToEntity(page: Page, searchEntity: SearchEntity) {
        whenever(mapper.mapToEntity(page))
            .thenReturn(searchEntity)
    }

}
