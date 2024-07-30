package com.agnes.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel(){
    val postsRepo = PostsRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Post>>()

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
}