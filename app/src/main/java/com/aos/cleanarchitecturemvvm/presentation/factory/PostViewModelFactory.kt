package com.aos.cleanarchitecturemvvm.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel

class PostViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(getPostsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}