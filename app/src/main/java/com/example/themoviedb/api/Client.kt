package com.example.themoviedb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    val Base_URL = "https://api.themoviedb.org/3/"
    var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}