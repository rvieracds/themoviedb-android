package com.example.themoviedb.model

import com.google.gson.annotations.SerializedName

class PeopleResponse {
    @SerializedName("page")
    private  var page: Int = 0
    @SerializedName("results")
    private var results: List<People> = ArrayList()
    @SerializedName("total_results")
    private  var totalResults: Int = 0
    @SerializedName("total_pages")
    private  var totalPages: Int = 0

    fun getPage(): Int{
        return page
    }
    fun setPage(page: Int){
        this.page = page
    }
    fun getResults(): List<People>{
        return results
    }
    fun setResults(results:List<People>){
        this.results = results
    }
    fun gettotalResults(): Int{
        return totalResults
    }
    fun settotalResults(totalResults:Int){
        this.totalResults = totalResults
    }
    fun gettotalPages(): Int{
        return totalPages
    }
    fun settotalPages(totalPages: Int){
        this.totalPages = totalPages
    }
}
