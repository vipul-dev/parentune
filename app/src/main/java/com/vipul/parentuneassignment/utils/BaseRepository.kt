package com.vipul.parentuneassignment

import android.util.Log
import com.vipul.parentuneassignment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Singleton

@Singleton
abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        Resource.Failure(false, t.code(), t.response()?.errorBody()?.string(), "")
                    }
                    else -> {
                        Log.e("Error===>", t.message.toString())
                        Resource.Failure(true, null, null, t.message.toString())
                    }
                }
            }
        }
    }
}