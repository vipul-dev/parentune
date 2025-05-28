package com.vipul.parentuneassignment.models
import com.google.gson.annotations.SerializedName


data class PlansDataModel(

    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("error") var error: ArrayList<String> = arrayListOf()

)