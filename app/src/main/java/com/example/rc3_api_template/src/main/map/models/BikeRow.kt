package com.example.rc3_api_template.src.main.map.models

import com.google.gson.annotations.SerializedName

data class BikeRow(
    @SerializedName("Row")
    // 이렇게 마지막 list에 담긴 아이템들은 RoomsModel 타입의 리스트로 저장한다.
    val items: List<RentBikeStatus> = arrayListOf<RentBikeStatus>()
)