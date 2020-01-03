package com.example.themoviedb.model

import com.google.gson.annotations.SerializedName

class People {
    @SerializedName("profile_path")
    private var profilePath: String

    @SerializedName("adult")
    private var adult: Boolean = false

    @SerializedName("id")
    private var id: Int=1

    @SerializedName("name")
    private var name: String

    @SerializedName("popularity")
    private var popularity: Double=0.0

    @SerializedName("known_for")
    private var knownFor: List<KnownFor> = ArrayList()

    constructor(profilePath: String, adult: Boolean, name: String, knownFor: List<KnownFor>, id:Int, popularity: Double) {
        this.profilePath=profilePath
        this.adult=adult
        this.name=name
        this.id=id
        this.knownFor=knownFor
        this.popularity=popularity
    }

    fun getProfilePath():String{
        return profilePath
    }
    fun setProfilePath(profilePath: String){
        this.profilePath=profilePath
    }

    fun isAdult():Boolean{
        return adult
    }
    fun setAdult(adult: Boolean){
        this.adult=adult
    }

    fun getName():String{
        return name
    }
    fun setName(name:String){
        this.name=name
    }

    fun getKnownFor():List<KnownFor>{
        return knownFor
    }
    fun setKnownFor(knownFor:List<KnownFor>){
        this.knownFor=knownFor
    }

    fun getPopularity():Double{
        return popularity
    }
    fun setPopularity(popularity:Double){
        this.popularity=popularity
    }

}