package com.aos.cleanarchitecturemvvm.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aos.cleanarchitecturemvvm.domain.usecase.AddCommentUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetCommentsUseCase
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.CommentViewModel

class CommentViewModelFactory(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(addCommentUseCase, getCommentsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
