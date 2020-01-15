package com.example.themoviedb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Crew {
    @SerializedName("credit_id")
    @Expose
    var creditId: Int = 0

    @SerializedName("department")
    @Expose
    lateinit var department: String

    @SerializedName("gender")
    @Expose
    var gender: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int = -1

    @SerializedName("job")
    @Expose
    lateinit var job: String

    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null
}
