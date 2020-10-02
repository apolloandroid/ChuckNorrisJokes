package com.example.chucknorrisjokes.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrisjokes.data.Repository
import com.example.chucknorrisjokes.util.ConnectionStatus
import java.lang.IllegalArgumentException

class JokesViewModelFactory(
    private val repository: Repository,
    private val connectionStatus: ConnectionStatus
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java))
            return JokesViewModel(repository, connectionStatus) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}