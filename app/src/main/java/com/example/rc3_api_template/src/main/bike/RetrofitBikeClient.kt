package com.example.rc3_api_template.src.main.bike

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBikeClient {

    val sRetrofit = initRetrofit()

    private const val BIKE_URL = "http://openapi.seoul.go.kr:8088/72466678726775733131375451705a47/json/bikeList/1/1000/"

    private fun initRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BIKE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}