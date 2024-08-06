package com.agnes.postsapp.model

data class Comment(
    var postId : Int,
    var id : Int,
    val name : String,
    var email : String,
    var body : String
)
