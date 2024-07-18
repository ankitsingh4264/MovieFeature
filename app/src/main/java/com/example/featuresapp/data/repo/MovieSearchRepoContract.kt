package com.example.featuresapp.data.repo

import com.example.featuresapp.data.Result
import com.example.featuresapp.network.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepoContract {
   suspend fun getMoviesList(search:String): Flow<Result<List<MovieModel>?>>
}