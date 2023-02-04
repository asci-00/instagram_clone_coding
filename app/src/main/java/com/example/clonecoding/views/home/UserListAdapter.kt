package com.example.clonecoding.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clonecoding.R
import com.example.clonecoding.databinding.ItemUserViewBinding
import com.example.clonecoding.models.UserDTO

class UserListAdapter (): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private var data = mutableListOf<UserDTO>()
    private val TAG = "PostListAdapter"

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = data[position].id!!

    fun setUserList(userList: MutableList<UserDTO>) {
        data = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(binding: ItemUserViewBinding): RecyclerView.ViewHolder(binding.root) {
        private val profileImg = binding.profileImg
        private val name = binding.name

        fun bind(item: UserDTO) {
            profileImg.setImageResource(R.drawable.sample_user_image)
            name.text = item.name
        }
    }
}