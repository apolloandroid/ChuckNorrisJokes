package com.example.chucknorrisjokes.data.network

import com.example.chucknorrisjokes.data.model.Joke
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel(
    @SerializedName("type")
    @Expose
    val resultType: String,

    @SerializedName("value")
    @Expose
    val resultValue: List<Joke>
)