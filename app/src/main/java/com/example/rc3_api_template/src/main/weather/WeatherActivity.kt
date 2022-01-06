package com.example.rc3_api_template.src.main.weather


import android.os.Bundle
import com.example.rc3_api_template.config.BaseActivity
import com.example.rc3_api_template.databinding.ActivityWeatherBinding
import com.example.rc3_api_template.src.main.weather.models.WeatherResponse
import kotlin.math.roundToInt

class WeatherActivity : BaseActivity<ActivityWeatherBinding>(ActivityWeatherBinding::inflate),
    WeatherActivityInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val API_KEY = "8d0086ca1d22fe3106c0b3b7c0826b5e"
        val cityName = "Seoul"
        showLoadingDialog(this)
        WeatherService(this).tryGetWeather(cityName, API_KEY)
    }

    override fun onGetWeatherSuccess(response: WeatherResponse) {
        dismissLoadingDialog()
        binding.weatherTextView.text = ((response.main.temp-273.15).roundToInt()).toString() + "°"
        binding.weatherDescription.text = (response.weather[0].description).toString()
        binding.tvTempMin.text = "↓" + ((response.main.temp_min-273.15).roundToInt()).toString() + "°"
        binding.tvTempMax.text = "↑" + ((response.main.temp_max-273.15).roundToInt()).toString() + "°"
        binding.tvWindNum.text = (response.wind.speed).toString() + "m/s"
        binding.tvWindState.text = "풍향:" + (response.wind.deg).toString() + "°"
    }

    override fun onGetWeatherFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}