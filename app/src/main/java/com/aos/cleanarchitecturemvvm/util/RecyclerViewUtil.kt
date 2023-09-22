package com.aos.cleanarchitecturemvvm.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.aos.cleanarchitecturemvvm.presentation.adapter.PostAdapter
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel

fun RecyclerView.setupSwipeToDelete(viewModel: PostViewModel, postAdapter: PostAdapter) {
    val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val postToDelete = postAdapter.getPostAtPosition(position)
            viewModel.deletePost(postToDelete)
        }
    }

    val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
    itemTouchHelper.attachToRecyclerView(this)
}