package com.example.chucknorrisjokes.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentJokesBinding
import com.example.chucknorrisjokes.di.DaggerAppComponent
import com.example.chucknorrisjokes.di.JokesFragmentModule
import com.example.chucknorrisjokes.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class JokesFragment : Fragment() {
    @Inject
    lateinit var viewModel: JokesViewModel
    private lateinit var binding: FragmentJokesBinding
    private lateinit var jokesListAdapter: JokesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        injectFragment()
        initObservers()
        setJokesList()

        binding.buttonLoadJokes.setOnClickListener {
            viewModel.onButtonLoadJokesClick(binding.editTextNumberOfJokes.text?.toString()!!)
        }

        binding.editTextNumberOfJokes.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            changeHeightOfJokesList(hasFocus)
        }

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jokes, container, false)
        binding.lifecycleOwner = this
    }

    private fun injectFragment() {
        val component = DaggerAppComponent.builder()
            .jokesFragmentModule(JokesFragmentModule(requireContext()))
            .build()
        component?.inject(this)
    }

    private fun initObservers() {
        viewModel.jokes.observe(viewLifecycleOwner, {
            jokesListAdapter.jokesList = it
            jokesListAdapter.notifyDataSetChanged()
        })

        viewModel.removeFocusFromInputNumber.observe(viewLifecycleOwner, Observer { event ->
            if (event) binding.editTextNumberOfJokes.clearFocus()
        })

        viewModel.showNoInternetConnectionNotification.observe(
            viewLifecycleOwner,
            Observer { event ->
                if (event) Snackbar.make(
                    binding.root,
                    getString(R.string.shackbar_no_internet),
                    Snackbar.LENGTH_SHORT
                ).show()
            })

        viewModel.isEmptyQuery.observe(viewLifecycleOwner, Observer { event ->
            if (event) Snackbar.make(
                binding.root,
                getString(R.string.shackbar_empty_query),
                Snackbar.LENGTH_SHORT
            ).show()
        })

        viewModel.hideKeyBoard.observe(viewLifecycleOwner, Observer { event ->
            if (event) binding.root.hideKeyboard()
        })
    }

    private fun setJokesList() {
        jokesListAdapter = JokesListAdapter()
        binding.listJokes.adapter = jokesListAdapter
        binding.listJokes.setHasFixedSize(true)
    }

    private fun changeHeightOfJokesList(hasFocus: Boolean) {
        val params = binding.scrollView.layoutParams
        when (hasFocus) {
            true -> binding.scrollView.layoutParams.height /= 2
            false -> {
                binding.scrollView.layoutParams.height *= 2
                binding.editTextNumberOfJokes.clearFocus()
            }
        }
        binding.scrollView.layoutParams = params
    }
}