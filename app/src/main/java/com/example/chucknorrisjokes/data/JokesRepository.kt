package com.example.chucknorrisjokes.data

import com.example.chucknorrisjokes.data.model.Joke
import com.example.chucknorrisjokes.data.network.NetworkService
import javax.inject.Inject

class JokesRepository @Inject constructor(private val networkService: NetworkService) : Repository {
    override suspend fun loadJokes(number: Int): List<Joke>? = networkService.loadJokes(number)
}