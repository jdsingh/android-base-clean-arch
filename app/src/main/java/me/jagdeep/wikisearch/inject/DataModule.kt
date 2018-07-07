package me.jagdeep.wikisearch.inject

import dagger.Binds
import dagger.Module
import me.jagdeep.data.repository.SearchDataRepository
import me.jagdeep.domain.search.repository.SearchRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsSearchRepository(searchRepository: SearchDataRepository): SearchRepository

}
