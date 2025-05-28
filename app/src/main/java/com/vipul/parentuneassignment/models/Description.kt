package com.vipul.parentuneassignment.models

import com.google.gson.annotations.SerializedName


data class Description(

    @SerializedName("data_monthly") var dataMonthly: ArrayList<DataMonthly> = arrayListOf(),
    @SerializedName("data_annual") var dataAnnual: ArrayList<DataAnnual> = arrayListOf()

)