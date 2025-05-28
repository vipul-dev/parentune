package com.vipul.parentuneassignment.models

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("plan_id") var planId: Int? = null,
    @SerializedName("banner") var banner: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("claims") var claims: String? = null,
    @SerializedName("description") var description: Description? = Description(),
    @SerializedName("tagged_as") var taggedAs: String? = null,
    @SerializedName("plan_name") var planName: String? = null,
    @SerializedName("button_background") var buttonBackground: String? = null,
    @SerializedName("cost_per_day") var costPerDay: Int? = null,
    @SerializedName("cost_per_day_new") var costPerDayNew: Double? = null,
    @SerializedName("cta") var cta: String? = null

)