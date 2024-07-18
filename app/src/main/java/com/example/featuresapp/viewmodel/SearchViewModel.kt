package com.example.featuresapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featuresapp.data.Result
import com.example.featuresapp.data.repo.MovieSearchRepoContract
import com.example.featuresapp.network.model.MovieModel
import com.example.featuresapp.ui.views.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val searchRepo: MovieSearchRepoContract
) :ViewModel() {

    val movieListState = mutableStateOf<Result<List<MovieModel>>>(Result.Initial)
    var lastSearched = ""
    var filters = mutableListOf<FilterType>()
    var currentList = emptyList<MovieModel>()

    fun getSearchedMovies(search: String,paginatedCall:Boolean = false) {
        // no pagination if filters are applied
        if (paginatedCall && filters.isNotEmpty()) {
            return
        }
        //if new search is made clear the filters
        filters.clear()
        if (search.isBlank()){
            // reset
            currentList = emptyList()
            movieListState.value = Result.Initial
            return
        }
        lastSearched = search
        //repo call to get the movies
        viewModelScope.launch {
            searchRepo.getMoviesList(search).collectLatest {
                when (it) {
                    is Result.Success -> {
                        currentList = it.data ?: emptyList()
                        movieListState.value =
                            if (it.data == null) Result.Success(emptyList()) else Result.Success(it.data)
                    }

                    is Result.Error -> {
                        if (!paginatedCall)
                        movieListState.value = Result.Error(it.msg)
                    }

                    is Result.Loading -> {
                        if (!paginatedCall)
                        movieListState.value = Result.Loading
                    }
                    else -> {
                        //no-op
                    }
                }
            }
        }
    }

    fun setFilters() {

        for (filter in filters){
            currentList = when(filter){
                FilterType.YEAR -> {
                    currentList.sortedBy {
                        it.year
                    }
                }

                FilterType.RATING -> {
                    currentList.sortedWith { o1, o2 ->
                        o1.rating.compareTo(o2.rating)

                    }
                }
            }
        }
        //update the list after filters are applied
        movieListState.value = Result.Success(currentList)
    }
}





