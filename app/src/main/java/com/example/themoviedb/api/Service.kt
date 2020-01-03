package com.example.themoviedb.api

import com.example.themoviedb.model.MoviesResponse
import com.example.themoviedb.model.PeopleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("person/popular")
    fun getPopularPeople(@Query("api_key") apiKey: String): Call<PeopleResponse>
}