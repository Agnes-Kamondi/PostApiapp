package com.agnes.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agnes.postsapp.model.Comment
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.model.PostRequest
import com.agnes.postsapp.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel(){
    val postsRepo = PostsRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Post>>()
    val postLiveData =MutableLiveData<Post>()
    val commentsLiveData = MutableLiveData<List<Comment>>()
    val createPostLiveData = MutableLiveData<String>()

    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.fetchPost()
            if (response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun fetchPostById(postId:Int){
        viewModelScope.launch {
            val response = postsRepo.fetchPostById(postId)
            if(response.isSuccessful){
                postLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun fetchCommentsByPostId(postId: Int){
        viewModelScope.launch{
            val response = postsRepo.fetchCommentsByPostId(postId)
            if(response.isSuccessful){
                commentsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun createPost(postRequest: PostRequest){
        viewModelScope.launch {
            val response = postsRepo.createPost(postRequest)
            if(response.isSuccessful){
                createPostLiveData.postValue("Post Created Successfully")
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}