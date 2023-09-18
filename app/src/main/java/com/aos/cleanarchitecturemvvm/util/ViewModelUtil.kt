package com.aos.cleanarchitecturemvvm.util

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.aos.cleanarchitecturemvvm.presentation.MyApplication

fun getAppViewModelProvider(activity: Activity, factory: ViewModelProvider.Factory): ViewModelProvider {
    val app = activity.applicationContext as MyApplication
    return ViewModelProvider(app.getAppViewModelStore(), factory)
}