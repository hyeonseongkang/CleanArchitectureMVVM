package com.aos.cleanarchitecturemvvm.presentation.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.aos.cleanarchitecturemvvm.R
import com.aos.cleanarchitecturemvvm.data.local.LocalPostDataSource
import com.aos.cleanarchitecturemvvm.data.local.db.AppDatabase
import com.aos.cleanarchitecturemvvm.data.repository.PostRepositoryImpl
import com.aos.cleanarchitecturemvvm.databinding.ActivityCreatePostBinding
import com.aos.cleanarchitecturemvvm.domain.usecase.DeletePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.GetPostsUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.SearchPostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.UpdatePostUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.WritePostUseCase
import com.aos.cleanarchitecturemvvm.presentation.factory.PostViewModelFactory
import com.aos.cleanarchitecturemvvm.presentation.viewmodel.PostViewModel
import com.aos.cleanarchitecturemvvm.util.getAppViewModelProvider

class CreatePostActivity : AppCompatActivity() {

    private val IMAGE_PICK_REQUEST_CODE = 1001

    private lateinit var binding: ActivityCreatePostBinding

    private var selectedImageUris: MutableList<String> = mutableListOf()

    private val pickImagesLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        handleSelectedUris(uris)
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_post)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupClickListener()
        setupImageViewClickListener()

    }

    private fun setupClickListener() {
        binding.btnPost.setOnClickListener {
            viewModel.writePost(binding.etPostTitle.text.toString(),
            binding.etPostContent.text.toString(),
            selectedImageUris)
        }
    }

    private fun setupImageViewClickListener() {
        binding.ivPostPhoto.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        pickImagesLauncher.launch("image/*")
    }

    private fun handleSelectedUris(uris: List<Uri>) {
        selectedImageUris.addAll(uris.map { it.toString() })
    }

    private fun getPathFromURI(contentURI: Uri?): String? {
        val result: String?
        val cursor = contentResolver.query(contentURI!!, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
}