package me.jagdeep.wikisearch.main

import android.support.v7.util.DiffUtil
import me.jagdeep.domain.search.model.SearchResult

class SearchDiffCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult?, newItem: SearchResult?): Boolean {
        return oldItem?.pageId == newItem?.pageId
    }

    override fun areContentsTheSame(oldItem: SearchResult?, newItem: SearchResult?): Boolean {
        return oldItem == newItem
    }

}
