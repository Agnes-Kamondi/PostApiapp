package com.agnes.postsapp.repository

import com.agnes.postsapp.api.ApiClient
import com.agnes.postsapp.api.PostsApiInterface
import com.agnes.postsapp.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)

    suspend fun fetchPost(): Response<List<Post>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }
}