package com.vipul.parentuneassignment.repository

import com.vipul.parentuneassignment.BaseRepository
import com.vipul.parentuneassignment.di.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlansRepository @Inject constructor(private val apiService: ApiService):BaseRepository() {

    suspend fun getPlans()=safeApiCall {
        apiService.getPlans()
    }

}