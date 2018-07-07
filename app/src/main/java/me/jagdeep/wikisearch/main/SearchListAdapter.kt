package me.jagdeep.wikisearch.main

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.wikisearch.R
import me.jagdeep.wikisearch.base.GlideApp
import javax.inject.Inject

/**
 * SearchListAdapter and ViewHolder for SearchResult List View.
 */
class SearchListAdapter @Inject constructor() :
    ListAdapter<SearchResult, SearchListAdapter.SearchViewHolder>(SearchDiffCallback()) {

    var clickListener: SearchItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        if (adapterPosition == RecyclerView.NO_POSITION) return

        val item = getItem(adapterPosition)
        holder.bind(item, clickListener)
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val description = itemView.findViewById<TextView>(R.id.description)
        private val photo = itemView.findViewById<ImageView>(R.id.photo)

        fun bind(item: SearchResult, clickListener: SearchItemListener?) {
            itemView.setOnClickListener {
                clickListener?.onSearchResultClicked(item)
            }

            title.text = item.title
            description.text = item.description

            GlideApp.with(itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.wikipedia_logo)
                .apply(RequestOptions.centerCropTransform())
                .into(photo)
        }

    }

}
