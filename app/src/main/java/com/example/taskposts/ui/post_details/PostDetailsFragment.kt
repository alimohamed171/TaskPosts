package com.example.taskposts.ui.post_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.taskposts.R
import com.example.taskposts.databinding.FragmentPostBinding
import com.example.taskposts.databinding.FragmentPostDetailsBinding
import com.example.taskposts.models.PostModel
import com.example.taskposts.ui.post.PostViewModel

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {


    var _binding: FragmentPostDetailsBinding? =null
    val binding get() = _binding!!
    val postDetailsViewModel: PostDetailsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostDetailsBinding.bind(view)
        val id = arguments?.getInt("id")
        id?.let { id ->
            postDetailsViewModel.getPost(id)
        }

        observers()


    }

    private fun observers() {
        postDetailsViewModel.mutableLiveData.observe(viewLifecycleOwner, Observer {post ->
            if (post != null) {
                binding.textTitle.text = post.title
                binding.textBody.text = post.body
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}