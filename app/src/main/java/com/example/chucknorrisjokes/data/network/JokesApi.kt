package com.example.chucknorrisjokes.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApi {
    @GET("jokes/random/{number}")
    fun loadJokes(@Path("number") count: Int?): Call<ResponseModel>
}