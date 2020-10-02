package com.example.chucknorrisjokes.di

import com.example.chucknorrisjokes.ui.jokes.JokesFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [JokesFragmentModule::class])
@Singleton
interface AppComponent {
    fun inject(jokesFragment: JokesFragment)
}