package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesResponse {

    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null

}