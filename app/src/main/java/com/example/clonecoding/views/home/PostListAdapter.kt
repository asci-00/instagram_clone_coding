package com.example.clonecoding.views.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clonecoding.R
import com.example.clonecoding.databinding.ItemDefaultPostViewBinding
import com.example.clonecoding.databinding.ItemVideoPostViewBinding
import com.example.clonecoding.models.CommonPostDTO
import com.example.clonecoding.models.PostDTO

class PostListAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data = mutableListOf<CommonPostDTO>()
    private val TAG = "PostListAdapter"

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i(TAG, "viewType - $viewType");
        if (viewType == PostDTO.COMMON) {
            return CommonPostViewHolder(
                ItemDefaultPostViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            )
        }
        return VideoPostViewHolder(
            ItemVideoPostViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data[position].viewType) {
            PostDTO.COMMON -> (holder as CommonPostViewHolder).bind(data[position])
            PostDTO.VIDEO -> (holder as VideoPostViewHolder).bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = data[position].id!!

    override fun getItemViewType(position: Int): Int = data[position].viewType!!

    fun setPostList(postList: MutableList<CommonPostDTO>) {
        data = postList
        notifyDataSetChanged()
    }

    inner class CommonPostViewHolder(binding: ItemDefaultPostViewBinding): RecyclerView.ViewHolder(binding.root) {
        private val profileImg = binding.profileImg
        private val title = binding.title
        private val description = binding.description
        private val mainImg = binding.mainImg

        fun bind(item: CommonPostDTO) {
            profileImg.setImageResource(R.drawable.ic_account)
            title.text = item.title
            description.text = item.timestamp.toString()
            mainImg.setImageResource(R.drawable.sample_post_image)
        }
    }
    inner class VideoPostViewHolder(binding: ItemVideoPostViewBinding): RecyclerView.ViewHolder(binding.root) {
        private val profileImg = binding.profileImg
        private val title = binding.title
        private val description = binding.description
        private val mainImg = binding.mainImg

        fun bind(item: CommonPostDTO) {
            profileImg.setImageResource(R.drawable.ic_account)
            title.text = item.title
            description.text = item.timestamp.toString()
            mainImg.setImageResource(R.drawable.sample_post_image)
        }
    }
}