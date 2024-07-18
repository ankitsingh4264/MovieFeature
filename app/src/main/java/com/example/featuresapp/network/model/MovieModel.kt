package com.example.featuresapp.network.model

import android.media.Rating
import android.os.IBinder
import com.google.gson.annotations.SerializedName

data class MovieModel(
   @SerializedName("Title") val title: String,
   @SerializedName("Year") val year: String,
   val imdbID: String,
   @SerializedName("Type") val type: String,
   @SerializedName("Poster") val poster: String,
   val rating: Int = 0
)
