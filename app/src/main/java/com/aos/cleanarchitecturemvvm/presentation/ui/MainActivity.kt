package com.aos.cleanarchitecturemvvm.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aos.cleanarchitecturemvvm.R
import com.aos.cleanarchitecturemvvm.data.local.LocalPostDataSource
import com.aos.cleanarchitecturemvvm.data.local.db.AppDatabase
import com.aos.cleanarchitecturemvvm.data.repository.PostRepositoryImpl
import com.aos.cleanarchitecturemvvm.databinding.ActivityMainBinding
import com.aos.cleanarchitecturemvvm.domain.usecase.DeletePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.SearchPostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.UpdatePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.WritePostUseCase
import com.aos.cleanarchitecturemvvm.presentation.adapter.PostAdapter
import com.aos.cleanarchitecturemvvm.presentation.factory.PostViewModelFactory
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel
import com.aos.cleanarchitecturemvvm.util.getAppViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        // Database 및 Dao 초기화
        val postDao = AppDatabase.getDatabase(applicationContext).postDao()
        val postDataSource = LocalPostDataSource(postDao)

        // Repository 생성
        val postRepository = PostRepositoryImpl(postDataSource)

        // UseCase 생성
        val getPostsUseCase = GetPostsUseCase(postRepository)
        val deletePostUseCase = DeletePostUseCase(postRepository)
        val searchPostUseCase = SearchPostUseCase(postRepository)
        val writePostUseCase = WritePostUseCase(postRepository)
        val updatePostUseCase = UpdatePostUseCase(postRepository)

        // ViewModelFactory 생성, ViewModel 받기
        getAppViewModelProvider(this, PostViewModelFactory(getPostsUseCase, deletePostUseCase, searchPostUseCase, writePostUseCase, updatePostUseCase))
            .get(PostViewModel::class.java)
    }

    private val postAdapter = PostAdapter()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView()
        observePosts()

        viewModel.fetchPosts()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPosts.layoutManager = layoutManager
        binding.rvPosts.adapter = postAdapter
    }

    private fun observePosts() {
        viewModel.posts.observe(this, Observer { posts ->
            postAdapter.setPosts(posts)
        })
    }
}
