package com.aos.cleanarchitecturemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var TAG = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "${Thread.currentThread().name}1")

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "work.")
            val network1 = async { network(1) }
            val network2 = async {network(2) }
            Log.d(TAG, network1.await() + " " + network2.await())
        }
        Log.d(TAG, "${Thread.currentThread().name}2")

    }

    fun hello() {
        Log.d(TAG, "${Thread.currentThread().name} + hello!!")
    }

    suspend fun network(id: Int): String {
        delay(2000)
        return "network ${id}"
    }
}

