package com.example.chucknorrisjokes.data.network

import com.example.chucknorrisjokes.data.model.Joke
import retrofit2.Retrofit
import javax.inject.Inject

class JokesService @Inject constructor(retrofit: Retrofit) : NetworkService {
    private val jokesApi = retrofit.create(JokesApi::class.java)

    override fun loadJokes(number: Int): List<Joke>? {
        val response = jokesApi.loadJokes(number).execute()
        return response.body()?.resultValue
    }
}