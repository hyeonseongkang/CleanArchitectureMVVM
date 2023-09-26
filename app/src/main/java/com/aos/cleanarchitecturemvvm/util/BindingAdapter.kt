package com.aos.cleanarchitecturemvvm.util

import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aos.cleanarchitecturemvvm.R
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel
import com.bumptech.glide.Glide

@BindingAdapter("postOnClick")
fun bindPostOnClick(button: Button, viewModel: PostViewModel) {
    button.setOnClickListener {
        val title = (button.rootView.findViewById(R.id.et_post_title) as EditText).text.toString()
        val content = (button.rootView.findViewById(R.id.et_post_content) as EditText).text.toString()
        viewModel.writePost(title, content)
    }
}

@BindingAdapter("searchOnClick")
fun bindSearchOnClick(button: ImageButton, viewModel: PostViewModel) {
    button.setOnClickListener {
        val query = (button.rootView.findViewById(R.id.et_search) as EditText).text.toString()
        viewModel.searchPosts(query)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = Uri.parse(imgUrl).buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
