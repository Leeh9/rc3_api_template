package com.example.rc3_api_template.src.main.weather

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rc3_api_template.config.BaseActivity
import com.example.rc3_api_template.databinding.ActivityWeatherBinding
import com.example.rc3_api_template.src.main.weather.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt
import com.example.rc3_api_template.R

//class WeatherActivity : AppCompatActivity() {
//
//
//    lateinit var binding: ActivityWeatherBinding
//    val API_KEY = "8d0086ca1d22fe3106c0b3b7c0826b5e"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        window.statusBarColor = Color.parseColor("#000000")
//        binding = ActivityWeatherBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//
//        /*
//        1.data class 정의
//        2. api 인터페이스 정의
//        3. 레트로핏 클라이언트 생성
//        4. 리퀘스트 전송
//
//         */
//
//        val cityName = "Seoul"
//        getWeatherData(cityName, API_KEY)
//
//        binding.ibBack.setOnClickListener {
//            this.finish()
//        }
////        binding.weatherButton.setOnClickListener{
////            //val cityName = binding.weatherTextView.text.toString()
////            val cityName = "Seoul"
////            getWeatherData(cityName, API_KEY)
////        }
//    }
//
//    private fun getWeatherData(city : String, key : String) {
//        val weatherInterface = RetrofitWeatherClient.sRetrofit.create(WeatherInterface::class.java)
//        weatherInterface.getWeather(city, key).enqueue(object : Callback<WeatherResponse> {
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(
//                call: Call<WeatherResponse>,
//                response: Response<WeatherResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val result = response.body() as WeatherResponse
//                    binding.weatherTextView.text = ((result.main.temp-273.15).roundToInt()).toString() + "°"
//                    binding.weatherDescription.text = (result.weather[0].description).toString()
//                    binding.tvTempMin.text = "↓" + ((result.main.temp_min-273.15).roundToInt()).toString() + "°"
//                    binding.tvTempMax.text = "↑" + ((result.main.temp_max-273.15).roundToInt()).toString() + "°"
//                    binding.tvWindNum.text = (result.wind.speed).toString() + "m/s"
//                    binding.tvWindState.text = "풍향:" + (result.wind.deg).toString() + "°"
//                } else {
//                    Log.d("WeatherActivity", "Error")
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//                Log.d("WeatherActivity", t.message ?: "통신오류")
//            }
//        })
//    }
//
//
//}

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

//    override fun onPostSignUpSuccess(response: SignUpResponse) {
//        dismissLoadingDialog()
//        binding.homeBtnTryPostHttpMethod.text = response.message
//        response.message?.let { showCustomToast(it) }
//    }
//
//    override fun onPostSignUpFailure(message: String) {
//        dismissLoadingDialog()
//        showCustomToast("오류 : $message")
//    }
}