package com.agnes.postsapp.api

import com.agnes.postsapp.model.Comment
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.model.PostRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun fetchPostById(@Path("postId")postId : Int): Response<Post>

    @GET("/posts/{postId}/comments")
    suspend fun fetchCommentsByPostId(@Path("postId") postId: Int): Response<List<Comment>>

    @POST("/posts")
    suspend fun createPost(@Body postRequest: PostRequest): Response<Post>
}
