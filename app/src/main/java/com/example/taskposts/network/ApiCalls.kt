package com.example.taskposts.network

import com.example.taskposts.models.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCalls {

    @GET("posts")
     suspend fun getPosts(): List<PostModel>
     @GET("posts/{id}")
     suspend fun getPost(
         @Path("id")
         id :Int
     ): PostModel

}