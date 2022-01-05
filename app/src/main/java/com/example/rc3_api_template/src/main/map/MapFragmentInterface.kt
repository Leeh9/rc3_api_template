package com.example.rc3_api_template.src.main.map

import com.example.rc3_api_template.src.main.map.models.BikeResponse
import com.example.rc3_api_template.src.main.weather.models.WeatherResponse
import net.daum.mf.map.api.MapView

interface MapFragmentInterface {

    fun onGetBikeSuccess(response: BikeResponse, mapView: MapView)

    fun onGetBikeFailure(message: String)

}