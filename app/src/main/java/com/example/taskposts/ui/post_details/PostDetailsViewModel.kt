package com.example.taskposts.ui.post_details

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskposts.models.PostModel
import com.example.taskposts.repository.PostRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PostDetailsViewModel:ViewModel() {
    val mutableLiveData = MutableLiveData<PostModel>()
    val repo = PostRepository()
    fun getPost(id:Int){
        viewModelScope.launch(IO) {
            try {
                val data = repo.getPostDetails(id)
                mutableLiveData.postValue(data)
            }catch (e:Exception){
                Log.e("TAG", "getPost: $e.printStackTrace() ", )
            }
        }
    }
}