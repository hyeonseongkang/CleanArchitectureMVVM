package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.usecase.DeletePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.launch

class PostViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase.execute()
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            deletePostUseCase.execute(post)
            fetchPosts()
        }
    }

}