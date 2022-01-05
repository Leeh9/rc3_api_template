package com.example.rc3_api_template.src.main.map

import com.example.rc3_api_template.src.main.map.models.BikeResponse
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.http.GET

interface BikeRetrofitInterface {

    @GET("bike")
    fun getBike(mapView: MapView): Call<BikeResponse>
}