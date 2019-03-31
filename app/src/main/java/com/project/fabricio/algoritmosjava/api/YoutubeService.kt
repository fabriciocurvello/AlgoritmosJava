package com.project.fabricio.algoritmosjava.api

import com.project.fabricio.algoritmosjava.model.Resultado

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    /*

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyD4z9O_Ze-wdxXtYqHSwYX6zUZyZ4yj0G4
    &channelId=UCDKQmex_QOtjlZvWlRbjMew
    &q=pesquisa+usuario

     */

    @GET("search")
    fun recuperarVideos(
            @Query("part") part: String,
            @Query("order") order: String,
            @Query("maxResults") maxResults: String,
            @Query("key") key: String,
            @Query("channelId") channelId: String,
            @Query("q") q: String
    ): Call<Resultado>

}
