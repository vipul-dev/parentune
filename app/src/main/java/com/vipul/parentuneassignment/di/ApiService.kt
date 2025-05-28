package com.vipul.parentuneassignment.di

import com.vipul.parentuneassignment.models.PlansDataModel
import retrofit2.http.GET

interface ApiService {
    @GET("plans")
    suspend fun getPlans():PlansDataModel
}