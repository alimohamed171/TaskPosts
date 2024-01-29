package com.example.taskposts.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.taskposts.R
import com.example.taskposts.adapters.PostAdapter
import com.example.taskposts.databinding.FragmentPostBinding

class PostFragment : Fragment(R.layout.fragment_post) {

    var _binding: FragmentPostBinding? =null
    val binding get() = _binding!!
    val postViewModel: PostViewModel by viewModels()
    lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostBinding.bind(view)
        postAdapter = PostAdapter()
        postViewModel.getPosts()
        observers()
        postAdapter.setOnItemClickListener {
            findNavController()
                .navigate(PostFragmentDirections.actionPostFragmentToPostDetailsFragment(it.id))
        }
    }

    private fun observers() {
       postViewModel.mutableLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                postAdapter.differ.submitList(it.toList())
            }
           binding.recycle.apply {
               adapter = postAdapter

           }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}