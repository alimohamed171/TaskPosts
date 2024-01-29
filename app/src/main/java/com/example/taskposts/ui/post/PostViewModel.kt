package com.example.taskposts.ui.post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskposts.models.PostModel
import com.example.taskposts.repository.PostRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostViewModel:ViewModel() {
    val mutableLiveData = MutableLiveData<List<PostModel>>()
    val repo = PostRepository()
    fun getPosts() {
        viewModelScope.launch(IO) {
            try {
                val data = repo.getPosts()
                mutableLiveData.postValue(data)
            } catch (e: Exception) {
                // Handle the exception, log it, or show an error message

                Log.e("TAG", "getPosts: $e.printStackTrace() ", )
            }
        }
    }
}