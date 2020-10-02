package com.example.chucknorrisjokes.data.network

import com.example.chucknorrisjokes.data.model.Joke

interface NetworkService {
    fun loadJokes(number: Int): List<Joke>?
}