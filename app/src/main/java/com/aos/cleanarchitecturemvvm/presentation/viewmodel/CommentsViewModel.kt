package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aos.cleanarchitecturemvvm.domain.model.Comment
import com.aos.cleanarchitecturemvvm.domain.usecase.AddCommentUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.launch

class CommentsViewModel(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    fun fetchComments(postId: Int) {
        viewModelScope.launch {
            _comments.value = getCommentsUseCase.execute(postId)
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch {
            addCommentUseCase.execute(comment)
            fetchComments(comment.postId) // 댓글 추가 후 댓글 목록 다시 가져오기
        }
    }
}