package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieImagesResponse {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("backdrops")
    @Expose
    var backdrops: ArrayList<MovieImage> = ArrayList()

    @SerializedName("posters")
    @Expose
    var posters: ArrayList<MovieImage> = ArrayList()
}
