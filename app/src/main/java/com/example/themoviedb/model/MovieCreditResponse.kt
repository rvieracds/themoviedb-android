package com.example.themoviedb.model

import com.google.gson.annotations.SerializedName

class MovieCreditResponse {
    @SerializedName("id")
    private var id: Int = 0

    @SerializedName("cast")
    private var cast: ArrayList<Cast> = ArrayList()

    @SerializedName("crew")
    private var crew: ArrayList<Crew> = ArrayList()

    fun getId(): Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }
    fun getCast(): ArrayList<Cast>{
        return cast
    }
    fun setCast(cast:ArrayList<Cast>){
        this.cast = cast
    }
    fun getCrew(): ArrayList<Crew>{
        return crew
    }
    fun setCrew(crew: ArrayList<Crew>){
        this.crew = crew
    }
}
