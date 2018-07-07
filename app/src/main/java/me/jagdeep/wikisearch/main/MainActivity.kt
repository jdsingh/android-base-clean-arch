package me.jagdeep.wikisearch.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.core.content.systemService
import androidx.core.view.isVisible
import androidx.core.widget.toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.jagdeep.domain.search.model.SearchResult
import me.jagdeep.presentation.SearchState
import me.jagdeep.presentation.SearchViewModel
import me.jagdeep.wikisearch.R
import me.jagdeep.wikisearch.inject.ViewModelFactory
import me.jagdeep.wikisearch.util.onScroll
import me.jagdeep.wikisearch.util.onTextChanged
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var listAdapter: SearchListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)

        setupListView()
        hideKeyboardOnScroll()
        observeState()

        query_input.onTextChanged {
            clear_query.isVisible = it.isNotEmpty()
            viewModel.search(it.toString())
        }
    }

    private fun observeState() {
        viewModel.searchResult().observe(this, Observer { state ->
            when (state) {
                is SearchState.Error -> {
                    // TODO: show nice error message
                    toast(state.message)
                }
                is SearchState.Success -> {
                    results.visibility = View.VISIBLE
                    listAdapter.submitList(state.result)
                }
            }
        })
    }

    private fun setupListView() {
        clear_query.setOnClickListener {
            query_input.setText("")
            listAdapter.submitList(emptyList())
        }

        listAdapter.clickListener = searchItemListener
        results.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun hideKeyboardOnScroll() {
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        var totalDy = 0
        results.onScroll { _, dy ->
            if (dy > 0) {
                totalDy += dy
                if (totalDy >= touchSlop) {
                    totalDy = 0

                    val inputMethodManager = systemService<InputMethodManager>()
                    inputMethodManager.hideSoftInputFromWindow(
                        query_input.windowToken,
                        HIDE_NOT_ALWAYS
                    )
                }
            }
        }
    }

    private val searchItemListener = object : SearchItemListener {

        override fun onSearchResultClicked(searchResult: SearchResult) {
            // TODO: open wikipedia page in chrome tab
        }

    }

}
