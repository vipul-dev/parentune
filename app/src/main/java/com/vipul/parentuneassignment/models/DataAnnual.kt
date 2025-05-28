package com.vipul.parentuneassignment.models

import com.google.gson.annotations.SerializedName


data class DataAnnual(

    @SerializedName("text") var text: String? = null,
    @SerializedName("isLocked") var isLocked: String? = null

)