package com.example.rc3_api_template.src.main.bike.models

data class RentBikeStatus(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)