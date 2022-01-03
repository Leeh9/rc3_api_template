package com.example.rc3_api_template.src.main.weather.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String
)