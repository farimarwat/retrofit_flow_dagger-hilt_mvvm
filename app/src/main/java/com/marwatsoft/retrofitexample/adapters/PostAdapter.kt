package com.marwatsoft.retrofitexample.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marwatsoft.retrofitexample.databinding.ItemPostBinding
import com.marwatsoft.retrofitexample.helpers.GlobalHelper
import com.marwatsoft.retrofitexample.models.Post

class PostAdapter : ListAdapter<Post,PostAdapter.PostHolder>(diffcalback) {
    companion object {
        private val diffcalback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }

        }
    }
    class PostHolder(binding:ItemPostBinding):RecyclerView.ViewHolder(binding.root){
        val txt_title = binding.txtTitle
        val txt_body = binding.txtBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val item = getItem(position)
        Log.e(GlobalHelper.TAG,item.title)
        holder.apply {
            txt_title.text = item.title
            txt_body.text = item.body
        }
    }
}