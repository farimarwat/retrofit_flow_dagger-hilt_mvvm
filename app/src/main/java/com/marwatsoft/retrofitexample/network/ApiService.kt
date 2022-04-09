package com.marwatsoft.retrofitexample.network

import com.marwatsoft.retrofitexample.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts():Response<List<Post>>
}