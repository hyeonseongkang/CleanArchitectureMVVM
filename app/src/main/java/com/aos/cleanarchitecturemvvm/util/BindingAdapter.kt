package com.aos.cleanarchitecturemvvm.util

import android.widget.Button
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.aos.cleanarchitecturemvvm.R
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel

@BindingAdapter("postOnClick")
fun bindPostOnClick(button: Button, viewModel: PostViewModel) {
    button.setOnClickListener {
        val title = (button.rootView.findViewById(R.id.et_post_title) as EditText).text.toString()
        val content = (button.rootView.findViewById(R.id.et_post_content) as EditText).text.toString()
        viewModel.writePost(title, content)
    }
}