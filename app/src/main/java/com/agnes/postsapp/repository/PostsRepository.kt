package com.agnes.postsapp.repository

import com.agnes.postsapp.api.ApiClient
import com.agnes.postsapp.api.PostsApiInterface
import com.agnes.postsapp.model.Comment
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.model.PostRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Invocation
import retrofit2.Response

class PostsRepository {
    val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)

    suspend fun fetchPost(): Response<List<Post>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }
    suspend fun fetchPostById(postId : Int):Response<Post>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPostById(postId)
        }
    }
    suspend fun fetchCommentsByPostId(postId: Int): Response<List<Comment>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchCommentsByPostId(postId)
        }
    }
    suspend fun createPost(postRequest: PostRequest): Response<Post>{
        return withContext(Dispatchers.IO){
            apiClient.createPost(postRequest)
        }
    }
}