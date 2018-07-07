package me.jagdeep.wikisearch.inject

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.jagdeep.data.source.SearchRemote
import me.jagdeep.remote.ApiService
import me.jagdeep.remote.ApiServiceFactory
import me.jagdeep.remote.SearchRemoteImpl
import me.jagdeep.wikisearch.BuildConfig
import java.io.File

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun file(application: Application): File {
            val file = File(application.cacheDir, "OkHttpCache")
            file.mkdirs()
            return file
        }

        @Provides
        @JvmStatic
        fun provideApiService(cacheDir: File): ApiService {
            return ApiServiceFactory.makeApiService(BuildConfig.DEBUG, cacheDir)
        }

    }

    @Binds
    abstract fun bindsSearchRemote(searchRemote: SearchRemoteImpl): SearchRemote

}
