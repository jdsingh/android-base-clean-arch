package me.jagdeep.remote.mapper

import me.jagdeep.remote.test.RemoteDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SearchEntityMapperTest {

    private val mapper = SearchEntityMapper()

    @Test
    fun mapToEntity() {
        val page = RemoteDataFactory.createSearchPage()

        val searchEntity = mapper.mapToEntity(page)

        assertEquals(page.pageid, searchEntity.pageId)
        assertEquals(page.title, searchEntity.title)
        assertEquals(page.terms?.description?.get(0), searchEntity.description)
        assertEquals(page.thumbnail?.source, searchEntity.imageUrl)
    }

}
