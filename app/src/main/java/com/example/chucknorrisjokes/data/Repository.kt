package com.example.chucknorrisjokes.data

import com.example.chucknorrisjokes.data.model.Joke

interface Repository {
    suspend fun loadJokes(number: Int): List<Joke>?
}