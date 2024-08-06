package com.agnes.postsapp.ui

import android.content.Intent
import com.agnes.postsapp.api.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.api.PostsApiInterface
import com.agnes.postsapp.databinding.ActivityMainBinding
import com.agnes.postsapp.viewmodel.PostsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postsViewModel : PostsViewModel by viewModels()
    val TAG = "MYTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"ONCREATE")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsViewModel.fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { postsList->
            displayPosts(postsList)
            Log.d(TAG,"ONRESUME")
        })

        postsViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
        binding.fabAddPost.setOnClickListener {
            startActivity(Intent(this,CreatePostActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"MAINACTIVITYTAG ONSTART")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"MAINACTIVITYTAG ONPAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"MAINACTIVITYTAG ONSTOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MAINACTIVITYTAG ONDESTROP")
    }
//    fun fetchPosts(){
//        val apiClient =
//            ApiClient.buildApiClient(PostsApiInterface::class.java)
//        val request = apiClient.fetchPosts()
//        request.enqueue(object : Callback<List<Post>>{
//            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
//                if (p1.isSuccessful){
//                    val posts = p1.body()
//                    if(posts != null) {
//                        displayPosts(posts)
//                    }
////                    displayPosts(posts!!)
//                    Toast.makeText(baseContext,"Fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    Toast.makeText(baseContext,p1.errorBody()?.string(),Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
//                Toast.makeText(baseContext,p1.message,Toast.LENGTH_LONG).show()
//            }
//        })
//    }

    fun displayPosts(posts: List<Post>){
        val postsAdapter = PostsAdapter(posts,this)
        binding.rvPosts.adapter = postsAdapter
    }

}