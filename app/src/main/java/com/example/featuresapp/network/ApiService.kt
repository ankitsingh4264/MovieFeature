package com.example.featuresapp.network

import com.example.featuresapp.network.model.SearchMovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("page") pageNo: Int,
    ):SearchMovieModel
}