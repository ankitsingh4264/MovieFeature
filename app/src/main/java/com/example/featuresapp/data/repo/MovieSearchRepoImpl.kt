package com.example.featuresapp.data.repo

import android.annotation.SuppressLint
import android.util.Log
import com.example.featuresapp.data.Result
import com.example.featuresapp.network.ApiService
import com.example.featuresapp.network.model.MovieModel
import com.example.featuresapp.network.model.SearchMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieSearchRepoImpl @Inject constructor (
    private val apiService: ApiService,

): MovieSearchRepoContract{

    var pageNo = 1;
    var lastSearched = ""
    var lastResponse :SearchMovieModel? = null

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getMoviesList(search: String): Flow<Result<List<MovieModel>?>> {
        var paginatedCall = false
      if (search == lastSearched && lastResponse?.response == true){
          pageNo++
          paginatedCall = true
      }else{
          pageNo = 1
      }
        lastSearched = search
        return flow {
              emit(Result.Loading)
              try {
                  val searchResult = apiService.getMovies(search,pageNo)

                  if (searchResult.response == false) {
                      emit(Result.Error(searchResult.error))
                      return@flow
                  }
                  var currentList = searchResult.searchList?: emptyList()
                  currentList =  currentList.map {
                      it.copy(
                          rating =  (1..10).random()
                      )
                  }
                  if (paginatedCall){
                      currentList = lastResponse?.searchList?.plus(currentList)?: emptyList()
                  }
                  lastResponse = searchResult.copy(
                      searchList = currentList
                  )

                  emit(Result.Success(currentList))

              } catch (e: Exception) {
                emit(Result.Error(e.message))
              }
       }
    }
}

