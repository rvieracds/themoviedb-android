package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonDetailsResponse {
    @SerializedName("birthday")
    @Expose
    lateinit var birthday: String
    @SerializedName("known_for_department")
    @Expose
    lateinit var knownForDepartment: String
    @SerializedName("deathday")
    @Expose
    lateinit var deathday: String
    @SerializedName("id")
    @Expose
    var id: Int=1
    @SerializedName("name")
    @Expose
    lateinit var name: String
    @SerializedName("also_known_as")
    @Expose
    lateinit var alsoKnownAs: ArrayList<String>
    @SerializedName("gender")
    @Expose
    lateinit var gender: String
    @SerializedName("biography")
    @Expose
    lateinit var biography: String
    @SerializedName("popularity")
    @Expose
    var popularity:Double=0.0
    @SerializedName("place_of_birth")
    @Expose
    lateinit var placeOfBirth: String
    @SerializedName("profile_path")
    @Expose
    lateinit var profilePath: String
    @SerializedName("adult")
    @Expose
    var adult: Boolean = false
    @SerializedName("imdb_id")
    @Expose
    lateinit var imdbId: String
    @SerializedName("homepage")
    @Expose
    lateinit var homepage: String

//    constructor(birthday: String, knownForDepartment: String,deathday:String,id:Int,
//                name:String,alsoKnownAs:ArrayList<String>,gender:String, biography:String,
//                placeOfBirth:String, profilePath:String,popularity:Double,
//                imdbId:String,adult:Boolean,homepage:String) {
//
//        this.birthday=birthday
//        this.knownForDepartment=knownForDepartment
//        this.deathday=deathday
//        this.id=id
//        this.name=name
//        this.alsoKnownAs=alsoKnownAs
//        this.gender=gender
//        this.biography=biography
//        this.placeOfBirth=placeOfBirth
//        this.profilePath=profilePath
//        this.popularity=popularity
//        this.imdbId=imdbId
//        this.adult=adult
//        this.homepage=homepage
//    }

}
