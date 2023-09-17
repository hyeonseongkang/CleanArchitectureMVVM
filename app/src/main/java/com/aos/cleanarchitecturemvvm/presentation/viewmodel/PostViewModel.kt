package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.usecase.DeletePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.SearchPostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.UpdatePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.WritePostUseCase
import kotlinx.coroutines.launch

class PostViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val searchPostUseCase: SearchPostUseCase,
    private val writePostUseCase: WritePostUseCase,
    private val updatePostUseCase: UpdatePostUseCase
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _currentPost = MutableLiveData<Post>()
    val currentPost: LiveData<Post> = _currentPost

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

    fun searchPosts(query: String) {
        viewModelScope.launch {
            _posts.value = searchPostUseCase.execute(query)
        }
    }

    fun writePost(title: String, content: String) {
        val post = Post(id = 0, title = title, content = content)
        viewModelScope.launch {
            val newPost = writePostUseCase.execute(post)
            _currentPost.value = newPost

            val updatedPosts = _posts.value?.toMutableList()
            updatedPosts?.add(newPost)
            _posts.value = updatedPosts
        }
    }

    fun toggleFavorite(post: Post) {
        viewModelScope.launch {
            val updatedPost = post.copy(isFavorite = !post.isFavorite)
            updatePostUseCase.execute(updatedPost)
            fetchPosts()
        }
    }
}