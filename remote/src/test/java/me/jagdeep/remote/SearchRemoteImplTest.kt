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
        verify(service).search(
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any()
        )
    }

    @Test
    fun `search should call server with correct arguments`() {
        stubApiServiceSearch(Single.just(RemoteDataFactory.createSearchResponse()))

        searchRemote.search("Jagdeep").test()
        verify(service).search(
            eq("Jagdeep"),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any(),
            any()
        )
    }

    @Test
    fun `search should return response`() {
        val query = "Jagdeep"
        val response = RemoteDataFactory.createSearchResponse()
        stubApiServiceSearch(Single.just(response))
        val entities = mutableListOf<SearchEntity>()
        response.query.pages
            .sortedBy { it.index }
            .forEach {
                val entity = RemoteDataFactory.createSearchEntity()
                entities.add(entity)
                stubMapperMapToEntity(query to it, entity)
            }

        val testObserver = searchRemote.search(query).test()

        testObserver
            .assertValue(entities)
    }

    private fun stubApiServiceSearch(response: Single<SearchResponse>) {
        whenever(
            service.search(
                eq("Jagdeep"),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(response)
    }

    private fun stubMapperMapToEntity(item: Pair<String, Page>, searchEntity: SearchEntity) {
        whenever(mapper.mapToEntity(eq(item)))
            .thenReturn(searchEntity)
    }

}
