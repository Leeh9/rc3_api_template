package com.example.rc3_api_template.src.main.map.models

import com.google.gson.annotations.SerializedName

data class RentBikeStatus(
    @SerializedName("RESULT") val RESULT: RESULT,
    @SerializedName("list_total_count") val list_total_count: Int,
    @SerializedName("row") val row: List<Row>
)