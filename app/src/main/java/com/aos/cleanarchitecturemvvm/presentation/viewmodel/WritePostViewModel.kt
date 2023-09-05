package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.usecase.WritePostUseCase
import kotlinx.coroutines.launch

class WritePostViewModel(private val writePostUseCase: WritePostUseCase) : ViewModel() {

    private val _postResult = MutableLiveData<Post>()
    val postResult: LiveData<Post> = _postResult

    fun writePost(title: String, content: String) {
        val post = Post(id = 0, title = title, content = content)
        viewModelScope.launch {
            _postResult.value = writePostUseCase.execute(post)
        }
    }
}