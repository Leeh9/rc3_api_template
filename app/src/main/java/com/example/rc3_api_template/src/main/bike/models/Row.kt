package com.example.rc3_api_template.src.main.bike.models

data class Row(
    val parkingBikeTotCnt: String,
    val rackTotCnt: String,
    val shared: String,
    val stationId: String,
    val stationLatitude: String,
    val stationLongitude: String,
    val stationName: String
)