package me.jagdeep.wikisearch.main

import android.content.Context
import android.support.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.wikisearch.R
import javax.inject.Inject

class OpenWikipediaPageHandler @Inject constructor(
    private val context: Context
) : ItemHandler {

    override operator fun invoke(searchResult: SearchResult) {
        val primary = context.getColor(R.color.primary)
        val uri = "https://en.m.wikipedia.org/?curid=${searchResult.pageId}".toUri()

        CustomTabsIntent.Builder()
            .setToolbarColor(primary)
            .addDefaultShareMenuItem()
            .enableUrlBarHiding()
            .build()
            .launchUrl(context, uri)
    }

}
