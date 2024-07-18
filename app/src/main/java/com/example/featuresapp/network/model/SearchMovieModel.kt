package com.example.featuresapp.network.model

import com.google.gson.annotations.SerializedName

data class SearchMovieModel(
    @SerializedName("Search") val searchList: List<MovieModel>?,
    val totalResults: Int?,
    @SerializedName("Response")val response: Boolean?,
    @SerializedName("Error")val error: String?
)
