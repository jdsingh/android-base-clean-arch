package me.jagdeep.remote.test

import me.jagdeep.data.model.SearchEntity
import me.jagdeep.remote.model.*

object RemoteDataFactory {

    fun createSearchResponse(): SearchResponse {
        return SearchResponse(Query(createSearchPages(5)))
    }

    fun createSearchEntity(): SearchEntity {
        return SearchEntity(
            pageId = DataFactory.randomInt(),
            query = DataFactory.randomString(),
            index = DataFactory.randomInt(),
            title = DataFactory.randomString(),
            description = DataFactory.randomString(),
            imageUrl = DataFactory.randomString()
        )
    }

    fun createSearchEntities(number: Int): List<SearchEntity> {
        return mutableListOf<SearchEntity>().apply {
            (0..number).forEach {
                add(createSearchEntity())
            }
        }
    }

    fun createSearchPage(): Page {
        return Page(
            pageid = DataFactory.randomInt(),
            index = DataFactory.randomInt(),
            title = DataFactory.randomString(),
            thumbnail = Thumbnail(DataFactory.randomString()),
            terms = Terms(
                listOf(
                    DataFactory.randomString(),
                    DataFactory.randomString()
                )
            )
        )
    }

    fun createSearchPages(number: Int): List<Page> {
        return mutableListOf<Page>().apply {
            (0..number).forEach {
                add(createSearchPage())
            }
        }
    }

}
