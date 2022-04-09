package com.marwatsoft.retrofitexample.utils

import com.marwatsoft.retrofitexample.models.Post

sealed class ApiStatus {
    data class Success(var list:List<Post>): ApiStatus()
    object Loading : ApiStatus()
    object Empty : ApiStatus()
    data class Error(var error:String): ApiStatus()
}
