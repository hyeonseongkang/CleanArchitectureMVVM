package com.aos.cleanarchitecturemvvm.presentation

import android.app.Application
import androidx.lifecycle.ViewModelStore


class MyApplication : Application() {
    private val appViewModelStore: ViewModelStore by lazy { ViewModelStore() }

    fun getAppViewModelStore(): ViewModelStore = appViewModelStore
}