package com.example.rc3_api_template.src.main.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("base") val base: String,
    @SerializedName("cod") val cod: Int,
    @SerializedName("dt") val dt: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: Main,
    @SerializedName("name") val name: String,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind
)