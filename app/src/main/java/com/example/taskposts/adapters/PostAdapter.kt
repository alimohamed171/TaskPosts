package com.example.taskposts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskposts.R
import com.example.taskposts.models.PostModel


class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        private val postTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val postDetails: TextView = itemView.findViewById(R.id.textBody)

        fun bind(post: PostModel) {
            postTitle.text = post.title
            postDetails.text = post.body

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(post)
                }
            }
        }
    }

    private val diffCallback = object :DiffUtil.ItemCallback<PostModel>(){
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
           return oldItem.body == newItem.body || oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this ,diffCallback)


    private var onItemClickListener: ((PostModel) -> Unit)? =null

    fun setOnItemClickListener(listener: (PostModel)->Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]

        holder.bind(post)
    }


}
