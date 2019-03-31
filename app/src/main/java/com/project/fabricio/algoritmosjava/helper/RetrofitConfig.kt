package com.project.fabricio.algoritmosjava.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    val retrofit: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(YouTubeConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

}
