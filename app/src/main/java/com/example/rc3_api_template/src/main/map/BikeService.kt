package com.example.rc3_api_template.src.main.map

import com.example.rc3_api_template.config.ApplicationClass
import com.example.rc3_api_template.src.main.map.models.BikeResponse
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BikeService (val mapFragmentInterface: MapFragmentInterface) {

    fun tryGetBike(mapView: MapView){
        val bikeRetrofitInterface = ApplicationClass.sRetrofit2.create(BikeRetrofitInterface::class.java)
        bikeRetrofitInterface.getBike(mapView).enqueue(object : Callback<BikeResponse> {
            override fun onResponse(call: Call<BikeResponse>, response: Response<BikeResponse>) {
                mapFragmentInterface.onGetBikeSuccess(response.body() as BikeResponse, mapView)
            }

            override fun onFailure(call: Call<BikeResponse>, t: Throwable) {
                mapFragmentInterface.onGetBikeFailure(t.message ?: "통신 오류")
            }
        })
    }

}