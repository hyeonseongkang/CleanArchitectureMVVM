package com.aos.cleanarchitecturemvvm.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aos.cleanarchitecturemvvm.domain.usecase.DeletePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.SearchPostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.WritePostUseCase
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel

class PostViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val searchPostUseCase: SearchPostUseCase,
    private val writePostUseCase: WritePostUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(
                getPostsUseCase,
                deletePostUseCase,
                searchPostUseCase,
                writePostUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}