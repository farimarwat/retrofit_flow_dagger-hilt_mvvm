package com.marwatsoft.retrofitexample.repository

import com.marwatsoft.retrofitexample.models.Post
import com.marwatsoft.retrofitexample.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

import javax.inject.Inject

class PostRepository @Inject constructor(var apiService:ApiService) {
     suspend fun getPosts():Flow<Response<List<Post>>> = flow{
          emit(apiService.getPosts())
     }
}