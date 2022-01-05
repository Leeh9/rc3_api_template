package com.example.rc3_api_template.src.main.map.models

import com.google.gson.annotations.SerializedName

data class BikeResponse(
    @SerializedName("rentBikeStatus") val rentBikeStatus: RentBikeStatus
)