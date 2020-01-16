package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieImage {
    @SerializedName("aspect_ratio")
    @Expose
    var aspectRatio: Int = 0

    @SerializedName("height")
    @Expose
    var height: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Int = 0

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0

    @SerializedName("file_path")
    @Expose
    var filePath: String? = null
}