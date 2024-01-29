package com.example.taskposts.repository

import com.example.taskposts.models.PostModel
import com.example.taskposts.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository {
    suspend fun getPosts()=RetrofitInstance.apiCalls.getPosts()
    suspend fun getPostDetails(id:Int)=RetrofitInstance.apiCalls.getPost(id)
}