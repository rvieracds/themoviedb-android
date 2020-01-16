package com.example.themoviedb.api

import com.example.themoviedb.model.*
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

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<MovieCreditResponse>

//    @GET("movie/{id}/images")
//    fun getMovieImages(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MovieImagesResponse>

    //    PEOPLE
    @GET("person/popular")
    fun getPopularPeople(@Query("api_key") apiKey: String): Call<PeopleResponse>

    @GET("person/{id}")
    fun getPersonDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<PersonDetailsResponse>

}