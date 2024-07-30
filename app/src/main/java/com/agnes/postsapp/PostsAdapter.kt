package com.agnes.postsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agnes.postsapp.databinding.CommentsListItemBinding
import com.agnes.postsapp.databinding.PostsListItemBinding

class PostsAdapter(var postsList: List<Post>, val context: Context): RecyclerView.Adapter<PostsViewVolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewVolder {
        val binding = PostsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostsViewVolder(binding)
    }

    override fun getItemCount(): Int {
        return  postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewVolder, position: Int) {
        val post = postsList[position]
        holder.binding.apply {
            tvBody.text = post.body
            tvTitle.text = post.title
            clPost.setOnClickListener {
                val intent = Intent(context, CommentsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                context.startActivity(intent)
            }
        }
    }

}
class PostsViewVolder(val binding: PostsListItemBinding):
        RecyclerView.ViewHolder(binding.root)

