package com.agnes.postsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    fun fetchPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun fetchPostById(@Path("postId")postId : Int): Call<Post>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId: Int): Call<List<Comment>>
}
