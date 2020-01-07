package com.example.themoviedb.model

import com.google.gson.annotations.SerializedName

class MovieCreditResponse {
    @SerializedName("id")
    private  var id: Int = 0
    @SerializedName("cast")
    private var cast: List<Cast> = ArrayList()

    fun getId(): Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }
    fun getCast(): List<Cast>{
        return cast
    }
    fun setCast(cast: List<Cast>){
        this.cast = cast
    }
}
