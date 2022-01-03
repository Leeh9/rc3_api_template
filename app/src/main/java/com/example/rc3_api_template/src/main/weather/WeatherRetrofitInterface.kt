package com.example.rc3_api_template.src.main.weather

import com.example.rc3_api_template.src.main.weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRetrofitInterface {
    @GET("weather")
    fun getWeather(@Query("q") q:String,
                   @Query("appid") appid:String
    ) : Call <WeatherResponse>
}
