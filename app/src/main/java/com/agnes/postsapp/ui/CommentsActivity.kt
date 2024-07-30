package com.agnes.postsapp.ui

import com.agnes.postsapp.api.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agnes.postsapp.model.Comment
import com.agnes.postsapp.model.Post
import com.agnes.postsapp.api.PostsApiInterface
import com.agnes.postsapp.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    val TAG = "MYTAG"
    private lateinit var binding: ActivityCommentsBinding
    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "COMMENTSACTIVITY ONCREATE")
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            fetchPost(postId)
            fetchCommentsByPostId(postId)
        }

        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "COMMENTSACTIVITY ONSTART")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "COMMENTSACTIVITY ONRESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "COMMENTSACTIVITY ONPAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "COMMENTSACTIVITY ONSTOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "COMMENTSACTIVITY ONDESTROY")
    }

    private fun setupRecyclerView() {
        commentsAdapter = CommentsAdapter(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = commentsAdapter
    }

    fun fetchPost(postId: Int) {
        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
        val request = apiClient.fetchPostById(postId)

        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                } else {
                    Toast.makeText(this@CommentsActivity, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchCommentsByPostId(postId: Int) {
        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
        val request = apiClient.fetchCommentsByPostId(postId)

        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()
                    if (comments.isNotEmpty()) {
                        commentsAdapter.commentsList = comments
                        commentsAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@CommentsActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


}
