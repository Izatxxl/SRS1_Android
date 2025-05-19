package com.example.praktika5_izat

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("todos/1")
    fun getTodo(): Call<Todo>

}

