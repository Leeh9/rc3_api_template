package com.example.rc3_api_template.src.main.weather

import com.example.rc3_api_template.src.main.weather.models.WeatherResponse

interface WeatherActivityInterface {

    fun onGetWeatherSuccess(response: WeatherResponse)

    fun onGetWeatherFailure(message: String)

}