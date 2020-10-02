package com.example.chucknorrisjokes.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorrisjokes.data.Repository
import com.example.chucknorrisjokes.data.model.Joke
import com.example.chucknorrisjokes.util.ConnectionStatus
import kotlinx.coroutines.*

class JokesViewModel(
    private val repository: Repository,
    private val connectionStatus: ConnectionStatus
) : ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>>
        get() = _jokes

    private val _removeFocusFromInputNumber = MutableLiveData<Boolean>()
    val removeFocusFromInputNumber: LiveData<Boolean>
        get() = _removeFocusFromInputNumber

    private val _showNoInternetConnectionNotification = MutableLiveData<Boolean>()
    val showNoInternetConnectionNotification: LiveData<Boolean>
        get() = _showNoInternetConnectionNotification

    private val _isEmptyQuery = MutableLiveData<Boolean>()
    val isEmptyQuery: LiveData<Boolean>
        get() = _isEmptyQuery

    private val _hideKeyboard = MutableLiveData<Boolean>()
    val hideKeyBoard: LiveData<Boolean>
        get() = _hideKeyboard

    fun onButtonLoadJokesClick(numberOfJokesText: String) {
        _removeFocusFromInputNumber.value = true
        _hideKeyboard.value = true
        if (showNoInternetConnectionIfNeed()) return
        if (numberOfJokesText.isEmpty()) {
            _isEmptyQuery.value = true
            return
        }
        val numberOfJokes = numberOfJokesText.toInt()
        loadJokes(numberOfJokes)
    }

    private fun loadJokes(numberOfJokes: Int) {
        viewModelScope.launch {
            _jokes.postValue(repository.loadJokes(numberOfJokes))
        }
    }

    private fun showNoInternetConnectionIfNeed(): Boolean {
        if (!connectionStatus.isOnline()) {
            _removeFocusFromInputNumber.value = true
            _showNoInternetConnectionNotification.value = true
            return true
        }
        return false
    }

    override fun onCleared() {
        viewModelScope.coroutineContext.cancel()
        super.onCleared()
    }
}


