package com.example.themoviedb.api

import com.example.themoviedb.model.MovieCreditResponse
import com.example.themoviedb.model.MoviesResponse
import com.example.themoviedb.model.PeopleResponse
import com.example.themoviedb.model.PersonDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    //    MOVIE
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MovieCreditResponse>

    //    PEOPLE
    @GET("person/popular")
    fun getPopularPeople(@Query("api_key") apiKey: String): Call<PeopleResponse>

    @GET("person/{id}")
    fun getPersonDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<PersonDetailsResponse>

}