package com.example.chucknorrisjokes.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.data.model.Joke
import com.example.chucknorrisjokes.databinding.ListJokesItemBinding

class JokesListAdapter : ListAdapter<Joke, JokeViewHolder>(DiffCallback) {
    var jokesList = listOf<Joke>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListJokesItemBinding.inflate(layoutInflater, parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) =
        holder.bind(jokesList[position])

    companion object DiffCallback : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.joke == newItem.joke
        }
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }
}

class JokeViewHolder(private val binding: ListJokesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        binding.joke = joke
    }
}
