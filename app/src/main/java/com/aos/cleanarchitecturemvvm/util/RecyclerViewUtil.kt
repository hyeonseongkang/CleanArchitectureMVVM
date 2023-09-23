package com.aos.cleanarchitecturemvvm.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.presentation.adapter.PostAdapter
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel

fun RecyclerView.setupSwipeToDeleteAndEdit(viewModel: PostViewModel, postAdapter: PostAdapter, activity: AppCompatActivity) {
    val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val post = postAdapter.getPostAtPosition(position)

            when (direction) {
                ItemTouchHelper.LEFT -> {
                    viewModel.deletePost(post)
                }

                ItemTouchHelper.RIGHT -> {
                    startEditPostActivity(post, activity)
                }
            }
        }
    }

    val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
    itemTouchHelper.attachToRecyclerView(this)
}

private fun startEditPostActivity(post: Post, activity: AppCompatActivity) {
//    val intent = Intent(activity, EditPostActivity::class.java)
//    intent.putExtra("postId", post.id)
//    activity.startActivity(intent)
}
