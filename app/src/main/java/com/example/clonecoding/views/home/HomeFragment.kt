package com.example.clonecoding.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clonecoding.databinding.FragmentHomeBinding
import com.example.clonecoding.debug.DummyData

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val TAG = "DetailViewFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val postAdapter = PostListAdapter()
        val userAdapter = UserListAdapter()

        postAdapter.setPostList(DummyData.postList)
        userAdapter.setUserList(DummyData.userList)

        binding.postListView.adapter = postAdapter
        binding.userListView.adapter = userAdapter

        return binding.root
    }
}