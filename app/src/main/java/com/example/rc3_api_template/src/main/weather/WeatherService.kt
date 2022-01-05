package com.example.rc3_api_template.src.main.weather

import com.example.rc3_api_template.config.ApplicationClass
import com.example.rc3_api_template.src.main.weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherService (val weatherActivityInterface: WeatherActivityInterface) {

    fun tryGetWeather(city : String, key : String){
        val weatherRetrofitInterface = ApplicationClass.sRetrofit2.create(WeatherRetrofitInterface::class.java)
        weatherRetrofitInterface.getWeather(city, key).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                weatherActivityInterface.onGetWeatherSuccess(response.body() as WeatherResponse)
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherActivityInterface.onGetWeatherFailure(t.message ?: "통신 오류")
            }
        })
    }

}