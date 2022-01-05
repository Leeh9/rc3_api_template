package com.example.rc3_api_template.src.main.map.models

import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("parkingBikeTotCnt") val parkingBikeTotCnt: String,
    @SerializedName("rackTotCnt") val rackTotCnt: String,
    @SerializedName("shared") val shared: String,
    @SerializedName("stationId") val stationId: String,
    @SerializedName("stationLatitude") val stationLatitude: String,
    @SerializedName("stationLongitude") val stationLongitude: String,
    @SerializedName("stationName") val stationName: String
)