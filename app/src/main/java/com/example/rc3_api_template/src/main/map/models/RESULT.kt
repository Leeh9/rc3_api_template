package com.example.rc3_api_template.src.main.map.models

import com.google.gson.annotations.SerializedName

data class RESULT(
    @SerializedName("CODE") val CODE: String,
    @SerializedName("MESSAGE") val MESSAGE: String
)