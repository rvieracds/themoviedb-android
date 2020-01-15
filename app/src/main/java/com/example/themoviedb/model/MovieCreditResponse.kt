package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieCreditResponse {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("cast")
    @Expose
    var cast: ArrayList<Cast> = ArrayList()

    @SerializedName("crew")
    @Expose
    var crew: ArrayList<Crew> = ArrayList()
}
