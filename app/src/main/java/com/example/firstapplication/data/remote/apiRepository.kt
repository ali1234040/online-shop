package com.example.firstapplication.data.remote

import android.util.Log
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit


suspend fun <T> safeGetData(request: suspend () -> T): Result<T> {
    return try {
        val response = request()
        Result.success(response)
    } catch (e: Exception) {
        when (e) {
            is SocketTimeoutException -> Log.e("TAG", "SocketTimeoutException")
            is IOException -> Log.e("TAG", "Connection error")
            is JsonSyntaxException -> Log.e("TAG", "JsonSyntaxException")
            else -> Log.e("TAG", "Unknown error : ${e.message}")
        }
        Result.failure(e)
    }
}
val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .build()

