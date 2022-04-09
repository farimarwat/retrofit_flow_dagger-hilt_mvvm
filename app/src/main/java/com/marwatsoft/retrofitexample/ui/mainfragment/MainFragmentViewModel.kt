package com.marwatsoft.retrofitexample.ui.mainfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.marwatsoft.retrofitexample.helpers.GlobalHelper
import com.marwatsoft.retrofitexample.models.Post
import com.marwatsoft.retrofitexample.repository.PostRepository
import com.marwatsoft.retrofitexample.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(var repo: PostRepository) : ViewModel() {
    var mPosts: MutableStateFlow<ApiStatus> = MutableStateFlow(ApiStatus.Empty)

    suspend fun getPosts() = viewModelScope.launch(Dispatchers.IO) {
        repo.getPosts()
            .catch { e->
               mPosts.value = ApiStatus.Error(e.message.toString())
            }
            .collect{
                val response = it
                if(response.isSuccessful){
                    val list = response.body()
                    list?.let {
                        mPosts.value = ApiStatus.Success(it)
                    }
                } else {
                    mPosts.value = ApiStatus.Error("Error occured")
                }
            }
    }
}