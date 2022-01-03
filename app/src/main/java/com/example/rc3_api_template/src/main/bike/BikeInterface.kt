package com.example.rc3_api_template.src.main.bike

import com.example.rc3_api_template.src.main.bike.models.BikeResponse
import retrofit2.Call
import retrofit2.http.GET

interface BikeInterface {

    @GET("bike")
    fun getBike(
    ) : Call<BikeResponse>
}