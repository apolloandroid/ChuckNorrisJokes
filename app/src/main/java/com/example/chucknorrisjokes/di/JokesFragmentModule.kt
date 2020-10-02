package com.example.chucknorrisjokes.di

import android.content.Context
import com.example.chucknorrisjokes.data.JokesRepository
import com.example.chucknorrisjokes.data.Repository
import com.example.chucknorrisjokes.data.network.JokesService
import com.example.chucknorrisjokes.data.network.NetworkService
import com.example.chucknorrisjokes.ui.jokes.JokesViewModel
import com.example.chucknorrisjokes.ui.jokes.JokesViewModelFactory
import com.example.chucknorrisjokes.util.ConnectionStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class JokesFragmentModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideJokesViewModel(
        repository: Repository,
        connectionStatus: ConnectionStatus
    ) = JokesViewModelFactory(repository, connectionStatus).create(JokesViewModel::class.java)

    @Singleton
    @Provides
    fun provideRepository(repository: JokesRepository): Repository = repository


    @Provides
    fun provideConnectionStatus(): ConnectionStatus = ConnectionStatus(context)

    @Singleton
    @Provides
    fun provideNetworkService(networkService: JokesService): NetworkService =
        networkService

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://api.icndb.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS).build()
}